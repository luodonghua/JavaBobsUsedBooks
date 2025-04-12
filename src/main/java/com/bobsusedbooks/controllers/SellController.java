package com.bobsusedbooks.controllers;

import com.bobsusedbooks.dtos.BookDto;
import com.bobsusedbooks.entities.Book;
import com.bobsusedbooks.entities.Customer;
import com.bobsusedbooks.services.BookService;
import com.bobsusedbooks.services.CustomerService;
import com.bobsusedbooks.services.ReferenceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sell")
public class SellController {
    
    private final BookService bookService;
    private final CustomerService customerService;
    private final ReferenceDataService referenceDataService;
    
    @Autowired
    public SellController(BookService bookService, 
                         CustomerService customerService,
                         ReferenceDataService referenceDataService) {
        this.bookService = bookService;
        this.customerService = customerService;
        this.referenceDataService = referenceDataService;
    }
    
    @GetMapping
    public String sellIndex(Model model) {
        // Get authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            Optional<Customer> customerOpt = customerService.findByUsername(auth.getName());
            if (customerOpt.isPresent()) {
                Customer customer = customerOpt.get();
                model.addAttribute("user", customer);
                
                // Get user's listings - only show books created by the current user
                List<BookDto> userListings = bookService.getAvailableUserListings(auth.getName());
                model.addAttribute("books", userListings);
                
                return "sell/index";
            }
        }
        
        // If not authenticated, redirect to login
        return "redirect:/login";
    }
    
    @GetMapping("/new")
    public String newBookForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            BookDto bookDto = new BookDto();
            bookDto.setIsAvailable(true); // Explicitly set isAvailable to true
            model.addAttribute("book", bookDto);
            model.addAttribute("genres", referenceDataService.getAllGenres());
            model.addAttribute("conditions", referenceDataService.getAllConditions());
            model.addAttribute("publishers", referenceDataService.getAllPublishers());
            model.addAttribute("bookTypes", referenceDataService.getAllBookTypes());
            return "sell/new";
        }
        return "redirect:/login";
    }
    
    @PostMapping("/new")
    public String createBookListing(@Valid @ModelAttribute("book") BookDto bookDto, 
                                   BindingResult result,
                                   @RequestParam(value = "coverImage", required = false) MultipartFile coverImage,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getName().equals("anonymousUser")) {
            return "redirect:/login";
        }
        
        if (result.hasErrors()) {
            model.addAttribute("genres", referenceDataService.getAllGenres());
            model.addAttribute("conditions", referenceDataService.getAllConditions());
            model.addAttribute("publishers", referenceDataService.getAllPublishers());
            model.addAttribute("bookTypes", referenceDataService.getAllBookTypes());
            return "sell/new";
        }
        
        try {
            // Get current user
            Optional<Customer> customerOpt = customerService.findByUsername(auth.getName());
            if (customerOpt.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "User account not found.");
                return "redirect:/sell";
            }
            
            Customer customer = customerOpt.get();
            
            // Set book as user listing and ensure isAvailable is true
            bookDto.setIsUserListing(true);
            bookDto.setIsAvailable(true); // Explicitly ensure isAvailable is true
            bookDto.setListingStatus(0); // 0 = For Sale
            bookDto.setSellerId(customer.getId());
            
            // Set createdBy to current username and createdOn to current time
            bookDto.setCreatedBy(auth.getName());
            bookDto.setCreatedOn(java.time.LocalDateTime.now());
            
            // Handle cover image if provided
            if (coverImage != null && !coverImage.isEmpty()) {
                try {
                    // Define upload directory - use absolute path
                    String uploadDir = System.getProperty("user.dir") + "/uploads/covers";
                    
                    // Save the file and get the filename
                    String fileName = com.bobsusedbooks.utils.FileUploadUtil.saveFile(uploadDir, coverImage);
                    
                    // Set the URL in the bookDto
                    if (fileName != null) {
                        bookDto.setCoverImageUrl("/uploads/covers/" + fileName);
                    } else {
                        bookDto.setCoverImageUrl("/images/placeholder-cover.jpg");
                    }
                } catch (Exception e) {
                    // Log the error but continue with default image
                    System.err.println("Error uploading image: " + e.getMessage());
                    bookDto.setCoverImageUrl("/images/placeholder-cover.jpg");
                }
            } else {
                // No image provided, use placeholder
                bookDto.setCoverImageUrl("/images/placeholder-cover.jpg");
            }
            
            // Save the book
            BookDto savedBook = bookService.createBook(bookDto);
            
            redirectAttributes.addFlashAttribute("message", "Your book has been listed successfully!");
            return "redirect:/sell";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred: " + e.getMessage());
            return "redirect:/sell";
        }
    }
    
    @PostMapping("/cancel/{id}")
    public String cancelListing(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getName().equals("anonymousUser")) {
            return "redirect:/login";
        }
        
        try {
            // Get current user
            Optional<Customer> customerOpt = customerService.findByUsername(auth.getName());
            if (customerOpt.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "User account not found.");
                return "redirect:/sell";
            }
            
            Customer customer = customerOpt.get();
            
            // Get the book
            Optional<BookDto> bookOpt = bookService.getBookById(id);
            if (bookOpt.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Book not found.");
                return "redirect:/sell";
            }
            
            BookDto book = bookOpt.get();
            
            // Verify that the book belongs to the current user
            if (!book.getSellerId().equals(customer.getId())) {
                redirectAttributes.addFlashAttribute("error", "You don't have permission to cancel this listing.");
                return "redirect:/sell";
            }
            
            // Cancel the listing
            book.setIsAvailable(false);
            book.setListingStatus(2); // 2 = Cancelled
            bookService.updateBook(book);
            
            redirectAttributes.addFlashAttribute("message", "Listing cancelled successfully.");
            return "redirect:/sell";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred: " + e.getMessage());
            return "redirect:/sell";
        }
    }
}
