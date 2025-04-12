package com.bobsusedbooks.services;

import com.bobsusedbooks.entities.Customer;
import org.springframework.stereotype.Service;

/**
 * This is a stub implementation of EmailService.
 * In a real application, this would send actual emails.
 * For this demo, we'll just log the email information.
 */
@Service
public class EmailService {
    
    public void sendOrderConfirmation(String to, String subject, String orderDetails) {
        // In a real application, this would send an email
        // For this demo, we'll just log it
        System.out.println("Sending order confirmation email to: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Order details: " + orderDetails);
    }
    
    public void sendOfferStatusUpdate(String to, String subject, String offerDetails) {
        // In a real application, this would send an email
        // For this demo, we'll just log it
        System.out.println("Sending offer status update email to: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Offer details: " + offerDetails);
    }
    
    public void sendPasswordResetLink(String to, String resetLink) {
        // In a real application, this would send an email
        // For this demo, we'll just log it
        System.out.println("Sending password reset email to: " + to);
        System.out.println("Reset link: " + resetLink);
    }
    
    public void sendPasswordResetEmail(Customer customer, String resetToken) {
        // In a real application, this would send an email with a reset link
        // For this demo, we'll just log it
        String resetLink = "http://localhost:8080/reset-password?token=" + resetToken;
        System.out.println("Sending password reset email to: " + customer.getEmail());
        System.out.println("Reset link: " + resetLink);
    }
}
