package com.bobsusedbooks.controllers;

import com.bobsusedbooks.dtos.ShoppingCartItemDto;
import com.bobsusedbooks.dtos.UserDto;
import com.bobsusedbooks.entities.Customer;
import com.bobsusedbooks.services.ShoppingCartService;
import com.bobsusedbooks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {
    
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    
    @Autowired
    public CartController(ShoppingCartService shoppingCartService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }
    
    @GetMapping
    public String viewCart(Model model) {
        // Get authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            Optional<Customer> customerOpt = userService.findByUsername(auth.getName());
            if (customerOpt.isPresent()) {
                Customer customer = customerOpt.get();
                List<ShoppingCartItemDto> cartItems = shoppingCartService.getCartItems(customer.getId());
                
                // Calculate total
                BigDecimal total = cartItems.stream()
                        .map(ShoppingCartItemDto::getTotalPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                
                model.addAttribute("cartItems", cartItems);
                model.addAttribute("total", total);
                model.addAttribute("user", userService.mapToUserDto(customer));
                return "cart/index";
            }
        }
        
        // If not authenticated, show empty cart
        model.addAttribute("cartItems", Collections.emptyList());
        model.addAttribute("total", BigDecimal.ZERO);
        return "cart/index";
    }
    
    @GetMapping("/add/{bookId}")
    public String addToCart(@PathVariable Integer bookId, RedirectAttributes redirectAttributes) {
        // Get authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            Optional<Customer> customerOpt = userService.findByUsername(auth.getName());
            if (customerOpt.isPresent()) {
                Customer customer = customerOpt.get();
                try {
                    shoppingCartService.addToCart(customer.getId(), Long.valueOf(bookId), 1);
                    redirectAttributes.addFlashAttribute("message", "Book added to cart");
                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("error", "Error adding book to cart: " + e.getMessage());
                }
                return "redirect:/books";
            }
        }
        
        // If not authenticated, redirect to login
        return "redirect:/login";
    }
    
    @GetMapping("/remove/{bookId}")
    public String removeFromCart(@PathVariable Integer bookId, RedirectAttributes redirectAttributes) {
        // Get authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            Optional<Customer> customerOpt = userService.findByUsername(auth.getName());
            if (customerOpt.isPresent()) {
                Customer customer = customerOpt.get();
                try {
                    shoppingCartService.removeFromCart(customer.getId(), Long.valueOf(bookId));
                    redirectAttributes.addFlashAttribute("message", "Item removed from cart");
                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("error", "Error removing item from cart: " + e.getMessage());
                }
            }
        }
        return "redirect:/cart";
    }
    
    @PostMapping("/update/{itemId}")
    public String updateCartItemQuantityWithPathVariable(
            @PathVariable Long itemId,
            @RequestParam Integer quantity,
            RedirectAttributes redirectAttributes) {
        
        if (quantity <= 0) {
            redirectAttributes.addFlashAttribute("error", "Quantity must be greater than zero");
            return "redirect:/cart";
        }
        
        // Get authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            try {
                shoppingCartService.updateCartItemQuantity(itemId, quantity);
                redirectAttributes.addFlashAttribute("message", "Cart updated");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Error updating cart: " + e.getMessage());
            }
        }
        
        return "redirect:/cart";
    }
    
    @GetMapping("/clear")
    public String clearCart(RedirectAttributes redirectAttributes) {
        // Get authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            Optional<Customer> customerOpt = userService.findByUsername(auth.getName());
            if (customerOpt.isPresent()) {
                Customer customer = customerOpt.get();
                shoppingCartService.clearCart(customer.getId());
                redirectAttributes.addFlashAttribute("message", "Cart cleared");
                return "redirect:/cart";
            }
        }
        
        return "redirect:/login";
    }
    
    @GetMapping("/checkout")
    public String checkout(Model model) {
        // Get authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            Optional<Customer> customerOpt = userService.findByUsername(auth.getName());
            if (customerOpt.isPresent()) {
                Customer customer = customerOpt.get();
                List<ShoppingCartItemDto> cartItems = shoppingCartService.getCartItems(customer.getId());
                
                if (cartItems.isEmpty()) {
                    model.addAttribute("error", "Your cart is empty");
                    return "redirect:/cart";
                }
                
                // Calculate total
                BigDecimal total = cartItems.stream()
                        .map(ShoppingCartItemDto::getTotalPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                
                model.addAttribute("cartItems", cartItems);
                model.addAttribute("total", total);
                model.addAttribute("user", userService.mapToUserDto(customer));
                return "cart/checkout";
            }
        }
        
        // If not authenticated, redirect to login
        return "redirect:/login";
    }
}
