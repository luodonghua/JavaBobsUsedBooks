package com.bobsusedbooks.controllers;

import com.bobsusedbooks.dtos.ShoppingCartItemDto;
import com.bobsusedbooks.entities.Customer;
import com.bobsusedbooks.services.CustomerService;
import com.bobsusedbooks.services.ShoppingCartService;
import com.bobsusedbooks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public String viewWishlist(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        Optional<Customer> customerOpt = customerService.findByUsername(username);
        
        if (customerOpt.isEmpty()) {
            return "redirect:/login";
        }
        
        Customer customer = customerOpt.get();
        List<ShoppingCartItemDto> wishlistItems = shoppingCartService.getWishlistItems(customer.getId());
        
        model.addAttribute("wishlistItems", wishlistItems);
        model.addAttribute("user", userService.mapToUserDto(customer));
        
        return "wishlist/index";
    }
    
    @GetMapping("/add/{bookId}")
    public String addToWishlistGet(@PathVariable Long bookId, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        Optional<Customer> customerOpt = customerService.findByUsername(username);
        
        if (customerOpt.isEmpty()) {
            return "redirect:/login";
        }
        
        Customer customer = customerOpt.get();
        try {
            ShoppingCartItemDto item = shoppingCartService.addToWishlist(customer.getId(), bookId);
            redirectAttributes.addFlashAttribute("message", "Book added to wishlist successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to add book to wishlist: " + e.getMessage());
        }
        
        // Redirect back to the book details page or referring page
        return "redirect:/books/" + bookId;
    }
    
    @PostMapping("/add/{bookId}")
    public ResponseEntity<?> addToWishlist(@PathVariable Long bookId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        Optional<Customer> customerOpt = customerService.findByUsername(username);
        
        if (customerOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not authenticated");
        }
        
        Customer customer = customerOpt.get();
        ShoppingCartItemDto item = shoppingCartService.addToWishlist(customer.getId(), bookId);
        
        return ResponseEntity.ok(item);
    }
    
    @GetMapping("/remove/{itemId}")
    public String removeFromWishlistGet(@PathVariable Long itemId, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        Optional<Customer> customerOpt = customerService.findByUsername(username);
        
        if (customerOpt.isEmpty()) {
            return "redirect:/login";
        }
        
        try {
            shoppingCartService.removeCartItem(itemId);
            redirectAttributes.addFlashAttribute("message", "Item removed from wishlist successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to remove item from wishlist: " + e.getMessage());
        }
        
        return "redirect:/wishlist";
    }
    
    @DeleteMapping("/remove/{itemId}")
    public ResponseEntity<?> removeFromWishlist(@PathVariable Long itemId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        Optional<Customer> customerOpt = customerService.findByUsername(username);
        
        if (customerOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not authenticated");
        }
        
        shoppingCartService.removeCartItem(itemId);
        
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/move-to-cart/{itemId}")
    public String moveToCartGet(@PathVariable Long itemId, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        Optional<Customer> customerOpt = customerService.findByUsername(username);
        
        if (customerOpt.isEmpty()) {
            return "redirect:/login";
        }
        
        Customer customer = customerOpt.get();
        try {
            ShoppingCartItemDto item = shoppingCartService.moveToCart(itemId, customer.getId());
            redirectAttributes.addFlashAttribute("message", "Item moved to cart successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to move item to cart: " + e.getMessage());
        }
        
        return "redirect:/wishlist";
    }
    
    @PostMapping("/move-to-cart/{itemId}")
    public ResponseEntity<?> moveToCart(@PathVariable Long itemId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        Optional<Customer> customerOpt = customerService.findByUsername(username);
        
        if (customerOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not authenticated");
        }
        
        Customer customer = customerOpt.get();
        ShoppingCartItemDto item = shoppingCartService.moveToCart(itemId, customer.getId());
        
        return ResponseEntity.ok(item);
    }
    
    @GetMapping("/api")
    @ResponseBody
    public List<ShoppingCartItemDto> getWishlistItems() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        Optional<Customer> customerOpt = customerService.findByUsername(username);
        
        if (customerOpt.isEmpty()) {
            return List.of();
        }
        
        Customer customer = customerOpt.get();
        List<ShoppingCartItemDto> wishlistItems = shoppingCartService.getWishlistItems(customer.getId());
        
        return wishlistItems;
    }
}
