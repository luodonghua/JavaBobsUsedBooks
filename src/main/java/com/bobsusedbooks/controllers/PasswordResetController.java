package com.bobsusedbooks.controllers;

import com.bobsusedbooks.entities.Customer;
import com.bobsusedbooks.services.PasswordResetService;
import com.bobsusedbooks.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class PasswordResetController {

    private final PasswordResetService passwordResetService;
    private final UserService userService;

    @Autowired
    public PasswordResetController(PasswordResetService passwordResetService, UserService userService) {
        this.passwordResetService = passwordResetService;
        this.userService = userService;
    }

    // Renamed to avoid conflict with AuthController
    @PostMapping("/generate-password-reset")
    public String generatePasswordReset(
            @RequestParam("email") String email,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        
        Optional<Customer> customer = userService.findByEmail(email);
        
        if (customer.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "No account found with that email address");
            return "redirect:/forgot-password";
        }
        
        // Create token
        String token = passwordResetService.createPasswordResetTokenForCustomer(customer.get());
        
        // Build reset URL
        String resetUrl = buildResetUrl(request, token);
        
        // For demo purposes, just show the URL in the flash attribute
        redirectAttributes.addFlashAttribute("message", 
                "Password reset instructions have been sent to your email. For demo purposes, here's the link: " + resetUrl);
        
        return "redirect:/login";
    }

    @GetMapping("/reset-password-token")
    public String displayTokenResetPage(@RequestParam("token") String token, Model model) {
        boolean isValid = passwordResetService.validatePasswordResetToken(token);
        
        if (!isValid) {
            model.addAttribute("error", "Invalid or expired password reset token");
            return "auth/invalid-token";
        }
        
        Optional<Customer> customer = passwordResetService.findCustomerByToken(token);
        if (customer.isEmpty()) {
            model.addAttribute("error", "User not found");
            return "auth/invalid-token";
        }
        
        model.addAttribute("token", token);
        return "auth/reset-password-token";
    }

    @PostMapping("/reset-password-token")
    public String handleTokenPasswordReset(
            @RequestParam("token") String token,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            RedirectAttributes redirectAttributes) {
        
        // Validate token
        boolean isValid = passwordResetService.validatePasswordResetToken(token);
        if (!isValid) {
            redirectAttributes.addFlashAttribute("error", "Invalid or expired password reset token");
            return "redirect:/forgot-password";
        }
        
        Optional<Customer> customer = passwordResetService.findCustomerByToken(token);
        if (customer.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "User not found");
            return "redirect:/forgot-password";
        }
        
        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Passwords do not match");
            redirectAttributes.addAttribute("token", token);
            return "redirect:/reset-password-token";
        }
        
        // Reset password
        boolean result = passwordResetService.resetPassword(token, password);
        
        if (result) {
            redirectAttributes.addFlashAttribute("message", "Password has been reset successfully. Please log in with your new password.");
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("error", "Error resetting password");
            return "redirect:/forgot-password";
        }
    }

    private String buildResetUrl(HttpServletRequest request, String token) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +
                request.getContextPath() + "/reset-password-token?token=" + token;
    }
}
