package com.bobsusedbooks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bobsusedbooks.dtos.CustomerDto;
import com.bobsusedbooks.dtos.OfferDto;
import com.bobsusedbooks.dtos.OrderDto;
import com.bobsusedbooks.services.BookService;
import com.bobsusedbooks.services.CustomerService;
import com.bobsusedbooks.services.OfferService;
import com.bobsusedbooks.services.OrderService;
import com.bobsusedbooks.services.ReferenceDataService;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookService bookService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private OfferService offerService;
    
    @Autowired
    private ReferenceDataService referenceDataService;

    @GetMapping
    public String adminDashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", auth.getName());
        return "admin/dashboard";
    }
    
    // Order Management
    
    @GetMapping("/orders")
    public String adminOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "admin/orders/view";
    }
    
    @GetMapping("/orders/{id}")
    public String viewOrder(@PathVariable Long id, Model model) {
        Optional<OrderDto> orderOpt = orderService.getOrderById(id);
        if (orderOpt.isPresent()) {
            model.addAttribute("order", orderOpt.get());
            return "admin/orders/details";
        } else {
            return "redirect:/admin/orders";
        }
    }
    
    @GetMapping("/orders/{id}/status/{status}")
    public String updateOrderStatus(@PathVariable Long id, @PathVariable String status, RedirectAttributes redirectAttributes) {
        try {
            int statusCode = 0;
            switch (status) {
                case "PROCESSING":
                    statusCode = 1;
                    break;
                case "SHIPPED":
                    statusCode = 2;
                    break;
                case "DELIVERED":
                    statusCode = 3;
                    break;
                case "CANCELLED":
                    statusCode = 4;
                    break;
                default:
                    statusCode = 0; // PENDING
            }
            
            orderService.updateOrderStatus(id, statusCode, status);
            redirectAttributes.addFlashAttribute("success", "Order status updated to " + status);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update order status: " + e.getMessage());
        }
        return "redirect:/admin/orders";
    }
    
    @PostMapping("/orders/{id}/update-status")
    public String updateOrderStatusForm(@PathVariable Long id, 
                                       @RequestParam String status,
                                       @RequestParam(required = false) String trackingNumber,
                                       RedirectAttributes redirectAttributes) {
        try {
            int statusCode = 0;
            switch (status) {
                case "PROCESSING":
                    statusCode = 1;
                    break;
                case "SHIPPED":
                    statusCode = 2;
                    break;
                case "DELIVERED":
                    statusCode = 3;
                    break;
                case "CANCELLED":
                    statusCode = 4;
                    break;
                default:
                    statusCode = 0; // PENDING
            }
            
            OrderDto updatedOrder = orderService.updateOrderStatus(id, statusCode, status);
            
            // If tracking number is provided and order is shipped, update tracking
            if (trackingNumber != null && !trackingNumber.isEmpty() && statusCode == 2) {
                // Implement tracking number update logic here
            }
            
            redirectAttributes.addFlashAttribute("success", "Order status updated to " + status);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update order status: " + e.getMessage());
        }
        return "redirect:/admin/orders/" + id;
    }
    
    // Customer Management
    
    @GetMapping("/customers")
    public String adminCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "admin/customers/index";
    }
    
    @GetMapping("/customers/{id}")
    public String viewCustomer(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<CustomerDto> customerOpt = customerService.getCustomerById(id);
        if (customerOpt.isPresent()) {
            model.addAttribute("customer", customerOpt.get());
            return "admin/customers/view";
        } else {
            redirectAttributes.addFlashAttribute("error", "Customer not found");
            return "redirect:/admin/customers";
        }
    }
    
    @GetMapping("/customers/{id}/edit")
    public String editCustomerForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<CustomerDto> customerOpt = customerService.getCustomerById(id);
        if (customerOpt.isPresent()) {
            model.addAttribute("customer", customerOpt.get());
            return "admin/customers/edit";
        } else {
            redirectAttributes.addFlashAttribute("error", "Customer not found");
            return "redirect:/admin/customers";
        }
    }
    
    @PostMapping("/customers/{id}/edit")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute CustomerDto customerDto, 
                                RedirectAttributes redirectAttributes) {
        try {
            customerDto.setId(id);
            CustomerDto updatedCustomer = customerService.updateCustomer(customerDto);
            redirectAttributes.addFlashAttribute("success", "Customer updated successfully");
            return "redirect:/admin/customers/" + updatedCustomer.getId();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update customer: " + e.getMessage());
            return "redirect:/admin/customers/" + id + "/edit";
        }
    }
    
    // Offer Management
    
    @GetMapping("/offers")
    public String adminOffers(Model model, 
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size) {
        org.springframework.data.domain.Pageable pageable = 
            org.springframework.data.domain.PageRequest.of(page, size);
        
        // Use customer books for sale instead of offers
        model.addAttribute("books", bookService.getCustomerBooksForSale(pageable));
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        return "admin/offers/index";
    }
    
    @GetMapping("/offers/{id}")
    public String viewOffer(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Optional<OfferDto> offerOpt = offerService.getOfferById(id);
            if (offerOpt.isPresent()) {
                model.addAttribute("offer", offerOpt.get());
                return "admin/offers/view";
            } else {
                redirectAttributes.addFlashAttribute("error", "Offer not found");
                return "redirect:/admin/offers";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Offer not found or could not be loaded: " + e.getMessage());
            return "redirect:/admin/offers";
        }
    }
    
    @GetMapping("/offers/{id}/accept")
    public String acceptOfferForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Optional<OfferDto> offerOpt = offerService.getOfferById(id);
            if (offerOpt.isPresent()) {
                model.addAttribute("offer", offerOpt.get());
                return "admin/offers/accept";
            } else {
                redirectAttributes.addFlashAttribute("error", "Offer not found");
                return "redirect:/admin/offers";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Offer not found or could not be loaded: " + e.getMessage());
            return "redirect:/admin/offers";
        }
    }
    
    @PostMapping("/offers/{id}/accept")
    public String acceptOffer(@PathVariable Long id, @RequestParam BigDecimal bookPrice, RedirectAttributes redirectAttributes) {
        try {
            offerService.acceptOffer(id, bookPrice);
            redirectAttributes.addFlashAttribute("success", "Offer accepted successfully");
            return "redirect:/admin/offers";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to accept offer: " + e.getMessage());
            return "redirect:/admin/offers/" + id;
        }
    }
    
    @GetMapping("/offers/{id}/reject")
    public String rejectOfferForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Optional<OfferDto> offerOpt = offerService.getOfferById(id);
            if (offerOpt.isPresent()) {
                model.addAttribute("offer", offerOpt.get());
                return "admin/offers/reject";
            } else {
                redirectAttributes.addFlashAttribute("error", "Offer not found");
                return "redirect:/admin/offers";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Offer not found or could not be loaded: " + e.getMessage());
            return "redirect:/admin/offers";
        }
    }
    
    @PostMapping("/offers/{id}/reject")
    public String rejectOffer(@PathVariable Long id, @RequestParam(required = false) String rejectionReason, RedirectAttributes redirectAttributes) {
        try {
            offerService.rejectOffer(id, rejectionReason);
            redirectAttributes.addFlashAttribute("success", "Offer rejected successfully");
            return "redirect:/admin/offers";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to reject offer: " + e.getMessage());
            return "redirect:/admin/offers/" + id;
        }
    }
    
    @GetMapping("/offers/{id}/delete")
    public String deleteOfferConfirmation(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Optional<OfferDto> offerOpt = offerService.getOfferById(id);
            if (offerOpt.isPresent()) {
                model.addAttribute("offer", offerOpt.get());
                return "admin/offers/delete";
            } else {
                redirectAttributes.addFlashAttribute("error", "Offer not found");
                return "redirect:/admin/offers";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Offer not found or could not be loaded: " + e.getMessage());
            return "redirect:/admin/offers";
        }
    }
    
    @PostMapping("/offers/{id}/delete")
    public String deleteOffer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            offerService.deleteOffer(id);
            redirectAttributes.addFlashAttribute("success", "Offer deleted successfully");
            return "redirect:/admin/offers";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete offer: " + e.getMessage());
            return "redirect:/admin/offers";
        }
    }
    
    // Other Admin Sections
    
    @GetMapping("/reference-data")
    public String adminReferenceData(Model model) {
        model.addAttribute("genres", referenceDataService.getAllGenres());
        model.addAttribute("publishers", referenceDataService.getAllPublishers());
        model.addAttribute("bookTypes", referenceDataService.getAllBookTypes());
        model.addAttribute("conditions", referenceDataService.getAllConditions());
        return "admin/reference-data/index";
    }
    
    @GetMapping("/reports")
    public String adminReports(Model model) {
        return "admin/reports/index";
    }
}
