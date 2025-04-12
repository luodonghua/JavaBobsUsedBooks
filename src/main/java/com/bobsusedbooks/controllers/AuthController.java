package com.bobsusedbooks.controllers;

import com.bobsusedbooks.dtos.CustomerDto;
import com.bobsusedbooks.dtos.LoginDto;
import com.bobsusedbooks.dtos.RegisterUserDto;
import com.bobsusedbooks.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "auth/login";
    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto loginDto, RedirectAttributes redirectAttributes) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword()
                    )
            );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }
    }
    
    @GetMapping("/register")
    public String registerForm(Model model) {
        // Changed from registerDto to userDto to match what the template expects
        model.addAttribute("userDto", new RegisterUserDto());
        return "auth/register";
    }
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userDto") RegisterUserDto registerDto, 
                           BindingResult result, 
                           RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            return "auth/register";
        }
        
        // Check if username exists
        if (userService.usernameExists(registerDto.getUsername())) {
            result.rejectValue("username", "error.username", "Username is already taken");
            return "auth/register";
        }
        
        // Check if email exists
        if (userService.emailExists(registerDto.getEmail())) {
            result.rejectValue("email", "error.email", "Email is already registered");
            return "auth/register";
        }
        
        try {
            CustomerDto registeredUser = userService.registerNewUser(registerDto);
            redirectAttributes.addFlashAttribute("success", "Registration successful! Please login.");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Registration failed: " + e.getMessage());
            return "redirect:/register";
        }
    }
    
    @GetMapping("/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "redirect:/login?logout";
    }
    
    @GetMapping("/forgot-password")
    public String forgotPasswordForm() {
        return "auth/forgot-password";
    }
    
    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email, RedirectAttributes redirectAttributes) {
        // Check if email exists
        if (!userService.emailExists(email)) {
            redirectAttributes.addFlashAttribute("error", "Email not found");
            return "redirect:/forgot-password";
        }
        
        // For this demo, we'll just redirect to a reset password page with a dummy token
        String dummyToken = "demo-token-" + System.currentTimeMillis();
        return "redirect:/reset-password?token=" + dummyToken + "&email=" + email;
    }
    
    @GetMapping("/reset-password")
    public String resetPasswordForm(@RequestParam String token, @RequestParam String email, Model model) {
        // For this demo, we'll just show the reset password form without token validation
        model.addAttribute("token", token);
        model.addAttribute("email", email);
        
        return "auth/reset-password";
    }
    
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token, 
                                @RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String confirmPassword,
                                RedirectAttributes redirectAttributes) {
        
        // Check if email exists
        if (!userService.emailExists(email)) {
            redirectAttributes.addFlashAttribute("error", "Email not found");
            return "redirect:/reset-password?token=" + token + "&email=" + email;
        }
        
        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Passwords do not match");
            return "redirect:/reset-password?token=" + token + "&email=" + email;
        }
        
        // For this demo, we'll just reset the password without token validation
        boolean success = userService.resetPassword(email, password);
        
        if (success) {
            redirectAttributes.addFlashAttribute("success", "Password reset successful! Please login.");
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("error", "Password reset failed");
            return "redirect:/reset-password?token=" + token + "&email=" + email;
        }
    }
}
