package com.bobsusedbooks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bobsusedbooks.dtos.OrderDto;
import com.bobsusedbooks.dtos.OrderItemDto;
import com.bobsusedbooks.dtos.CustomerDto;
import com.bobsusedbooks.entities.Customer;
import com.bobsusedbooks.services.CustomerService;
import com.bobsusedbooks.services.OrderService;
import com.bobsusedbooks.services.ShoppingCartService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private ShoppingCartService cartService;
    
    // API endpoints
    @GetMapping("/api/orders")
    @ResponseBody
    public ResponseEntity<List<OrderDto>> getCustomerOrders(Principal principal) {
        if (principal == null) {
            return ResponseEntity.badRequest().build();
        }
        
        Optional<Customer> customer = customerService.findByUsername(principal.getName());
        if (customer.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        List<OrderDto> orders = orderService.getOrdersByCustomerId(customer.get().getId());
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/api/orders/{id}")
    @ResponseBody
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id, Principal principal) {
        if (principal == null) {
            return ResponseEntity.badRequest().build();
        }
        
        Optional<OrderDto> orderOpt = orderService.getOrderById(id);
        if (orderOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        OrderDto order = orderOpt.get();
        
        // Verify the order belongs to the user
        Optional<Customer> customer = customerService.findByUsername(principal.getName());
        if (customer.isEmpty() || !order.getCustomerId().equals(customer.get().getId())) {
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok(order);
    }
    
    // Web endpoints
    @GetMapping("/orders/history")
    public String getOrderHistory(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Principal principal) {
        
        // For demo purposes, use a default customer ID
        Long customerId = 1L;
        
        // In a real application, you would get the customer from the principal
        if (principal != null) {
            Optional<Customer> customerOpt = customerService.findByUsername(principal.getName());
            if (customerOpt.isPresent()) {
                Customer customer = customerOpt.get();
                customerId = customer.getId();
                
                // Create a CustomerDto if needed for the view
                CustomerDto customerDto = new CustomerDto();
                customerDto.setId(customer.getId());
                customerDto.setFirstName(customer.getFirstName());
                customerDto.setLastName(customer.getLastName());
                customerDto.setEmail(customer.getEmail());
                model.addAttribute("user", customerDto);
            }
        }
        
        // Always sort by orderDate desc to show newest orders first
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "orderDate"));
        
        Page<OrderDto> orders = orderService.getOrdersByCustomerId(customerId, pageable);
        
        model.addAttribute("orders", orders);
        model.addAttribute("currentPage", page);
        
        return "orders/history";
    }
    
    @GetMapping("/orders/{id}")
    public String getOrderDetails(@PathVariable Long id, Model model, Principal principal) {
        // For demo purposes, use a default customer ID
        Long customerId = 1L;
        
        // In a real application, you would get the customer from the principal
        if (principal != null) {
            Optional<Customer> customerOpt = customerService.findByUsername(principal.getName());
            if (customerOpt.isPresent()) {
                Customer customer = customerOpt.get();
                customerId = customer.getId();
                
                // Create a CustomerDto if needed for the view
                CustomerDto customerDto = new CustomerDto();
                customerDto.setId(customer.getId());
                customerDto.setFirstName(customer.getFirstName());
                customerDto.setLastName(customer.getLastName());
                customerDto.setEmail(customer.getEmail());
                model.addAttribute("user", customerDto);
            }
        }
        
        Optional<OrderDto> orderOpt = orderService.getOrderById(id);
        if (orderOpt.isEmpty()) {
            return "redirect:/orders/history";
        }
        
        OrderDto order = orderOpt.get();
        
        // In a real application, verify the order belongs to the user
        // if (!order.getCustomerId().equals(customerId)) {
        //     return "redirect:/orders/history";
        // }
        
        // Debug: Print order details
        System.out.println("Order ID: " + order.getId());
        System.out.println("Order Items: " + (order.getItems() != null ? order.getItems().size() : "null"));
        if (order.getItems() != null) {
            for (OrderItemDto item : order.getItems()) {
                System.out.println("  Item: " + item.getBookTitle() + ", Price: " + item.getPrice() + ", Quantity: " + item.getQuantity());
            }
        }
        
        model.addAttribute("order", order);
        
        return "orders/details";
    }
    
    @PostMapping("/orders/place")
    public String placeOrder(RedirectAttributes redirectAttributes, Principal principal) {
        try {
            // For demo purposes, use a default customer ID
            Long customerId = 1L;
            
            // In a real application, you would get the customer from the principal
            if (principal != null) {
                Optional<Customer> customerOpt = customerService.findByUsername(principal.getName());
                if (customerOpt.isPresent()) {
                    customerId = customerOpt.get().getId();
                }
            }
            
            OrderDto order = orderService.createOrderFromCart(customerId, null);
            
            redirectAttributes.addFlashAttribute("message", "Order placed successfully! Order #" + order.getId());
            return "redirect:/orders/history";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to place order: " + e.getMessage());
            return "redirect:/cart/checkout";
        }
    }
    
    @PostMapping("/orders/{id}/cancel")
    public String cancelOrder(
            @PathVariable Long id, 
            @RequestParam(required = false) String reason,
            RedirectAttributes redirectAttributes,
            Principal principal) {
        
        try {
            OrderDto cancelledOrder = orderService.cancelOrder(id, reason != null ? reason : "Cancelled by customer");
            redirectAttributes.addFlashAttribute("message", "Order #" + id + " cancelled successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to cancel order: " + e.getMessage());
        }
        
        return "redirect:/orders/history";
    }
}
