package com.bobsusedbooks.controllers;

import com.bobsusedbooks.dtos.BookDto;
import com.bobsusedbooks.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/my-listings")
public class MyListingsController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String viewMyListings(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        // In a real app, you would get the seller ID from the authenticated user
        Long sellerId = 1L; // Placeholder
        
        List<BookDto> myListings = bookService.getBooksBySellerId(sellerId);
        model.addAttribute("myListings", myListings);
        
        return "my-listings/index";
    }
    
    @GetMapping("/create")
    public String createListingForm(Model model) {
        model.addAttribute("book", new BookDto());
        return "my-listings/create";
    }
    
    @PostMapping("/create")
    public String createListing(@ModelAttribute BookDto bookDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        // In a real app, you would get the seller ID from the authenticated user
        Long sellerId = 1L; // Placeholder
        bookDto.setSellerId(sellerId);
        bookDto.setIsUserListing(true);
        
        bookService.createBook(bookDto);
        
        return "redirect:/my-listings";
    }
    
    @GetMapping("/edit/{id}")
    public String editListingForm(@PathVariable Long id, Model model) {
        Optional<BookDto> bookDto = bookService.getBookById(id);
        
        if (bookDto.isEmpty()) {
            return "redirect:/my-listings";
        }
        
        // Check if the current user is the owner of this listing
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Long sellerId = 1L; // Placeholder - in a real app, get from authenticated user
        
        if (!sellerId.equals(bookDto.get().getSellerId())) {
            return "redirect:/my-listings";
        }
        
        model.addAttribute("book", bookDto.get());
        return "my-listings/edit";
    }
    
    @PostMapping("/edit/{id}")
    public String updateListing(@PathVariable Long id, @ModelAttribute BookDto bookDto) {
        Optional<BookDto> existingBook = bookService.getBookById(id);
        
        if (existingBook.isEmpty()) {
            return "redirect:/my-listings";
        }
        
        // Check if the current user is the owner of this listing
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Long sellerId = 1L; // Placeholder - in a real app, get from authenticated user
        
        if (!sellerId.equals(existingBook.get().getSellerId())) {
            return "redirect:/my-listings";
        }
        
        bookDto.setId(id);
        bookDto.setSellerId(sellerId);
        bookDto.setIsUserListing(true);
        
        bookService.updateBook(bookDto);
        
        return "redirect:/my-listings";
    }
    
    @PostMapping("/delete/{id}")
    public String deleteListing(@PathVariable Long id) {
        Optional<BookDto> existingBook = bookService.getBookById(id);
        
        if (existingBook.isEmpty()) {
            return "redirect:/my-listings";
        }
        
        // Check if the current user is the owner of this listing
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Long sellerId = 1L; // Placeholder - in a real app, get from authenticated user
        
        if (!sellerId.equals(existingBook.get().getSellerId())) {
            return "redirect:/my-listings";
        }
        
        bookService.deleteBook(id);
        
        return "redirect:/my-listings";
    }
    
    // API endpoints for AJAX operations
    
    @GetMapping("/api/listings")
    @ResponseBody
    public ResponseEntity<List<BookDto>> getMyListings() {
        // In a real app, you would get the seller ID from the authenticated user
        Long sellerId = 1L; // Placeholder
        
        List<BookDto> myListings = bookService.getBooksBySellerId(sellerId);
        return new ResponseEntity<>(myListings, HttpStatus.OK);
    }
}
