package com.bobsusedbooks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bobsusedbooks.dtos.ShoppingCartItemDto;
import com.bobsusedbooks.entities.Customer;
import com.bobsusedbooks.services.CustomerService;
import com.bobsusedbooks.services.ShoppingCartService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * ShoppingCartController.java is an API controller that might be used for:
1. AJAX calls from JavaScript in future
2. A potential mobile app or other API clients
3. Future functionality that's not yet fully implemented
 */
@RestController
@RequestMapping("/api/cart")
public class RESTShoppingCartController {

    @Autowired
    private ShoppingCartService cartService;
    
    @Autowired
    private CustomerService customerService;
    
    @GetMapping
    public ResponseEntity<List<ShoppingCartItemDto>> getCartItems(Principal principal) {
        if (principal == null) {
            return ResponseEntity.badRequest().build();
        }
        
        Optional<Customer> customer = customerService.findByUsername(principal.getName());
        if (customer.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        List<ShoppingCartItemDto> items = cartService.getCartItems(customer.get().getId());
        return ResponseEntity.ok(items);
    }
    
    @PostMapping("/add")
    public ResponseEntity<ShoppingCartItemDto> addToCart(
            @RequestParam Long bookId,
            @RequestParam(defaultValue = "1") int quantity,
            Principal principal) {
        
        if (principal == null) {
            return ResponseEntity.badRequest().build();
        }
        
        Optional<Customer> customer = customerService.findByUsername(principal.getName());
        if (customer.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        try {
            ShoppingCartItemDto item = cartService.addToCart(customer.get().getId(), bookId, quantity);
            return ResponseEntity.ok(item);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeFromCart(@RequestParam Long bookId, Principal principal) {
        if (principal == null) {
            return ResponseEntity.badRequest().build();
        }
        
        Optional<Customer> customer = customerService.findByUsername(principal.getName());
        if (customer.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        cartService.removeFromCart(customer.get().getId(), bookId);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/update")
    public ResponseEntity<Void> updateCartItemQuantity(
            @RequestParam Long itemId,
            @RequestParam int quantity,
            Principal principal) {
        
        if (principal == null) {
            return ResponseEntity.badRequest().build();
        }
        
        cartService.updateCartItemQuantity(itemId, quantity);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(Principal principal) {
        if (principal == null) {
            return ResponseEntity.badRequest().build();
        }
        
        Optional<Customer> customer = customerService.findByUsername(principal.getName());
        if (customer.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        cartService.clearCart(customer.get().getId());
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/wishlist")
    public ResponseEntity<List<ShoppingCartItemDto>> getWishlistItems(Principal principal) {
        if (principal == null) {
            return ResponseEntity.badRequest().build();
        }
        
        Optional<Customer> customer = customerService.findByUsername(principal.getName());
        if (customer.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        List<ShoppingCartItemDto> items = cartService.getWishlistItems(customer.get().getId());
        return ResponseEntity.ok(items);
    }
    
    @PostMapping("/wishlist/add")
    public ResponseEntity<ShoppingCartItemDto> addToWishlist(
            @RequestParam Long bookId,
            Principal principal) {
        
        if (principal == null) {
            return ResponseEntity.badRequest().build();
        }
        
        Optional<Customer> customer = customerService.findByUsername(principal.getName());
        if (customer.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        try {
            ShoppingCartItemDto item = cartService.addToWishlist(customer.get().getId(), bookId);
            return ResponseEntity.ok(item);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<Void> removeCartItem(@PathVariable Long itemId, Principal principal) {
        if (principal == null) {
            return ResponseEntity.badRequest().build();
        }
        
        cartService.removeCartItem(itemId);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/wishlist/move-to-cart/{itemId}")
    public ResponseEntity<ShoppingCartItemDto> moveToCart(@PathVariable Long itemId, Principal principal) {
        if (principal == null) {
            return ResponseEntity.badRequest().build();
        }
        
        Optional<Customer> customer = customerService.findByUsername(principal.getName());
        if (customer.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        try {
            ShoppingCartItemDto item = cartService.moveToCart(itemId, customer.get().getId());
            return ResponseEntity.ok(item);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
