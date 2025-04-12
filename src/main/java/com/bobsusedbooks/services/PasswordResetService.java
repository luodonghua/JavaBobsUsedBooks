package com.bobsusedbooks.services;

import com.bobsusedbooks.entities.Customer;
import com.bobsusedbooks.entities.PasswordResetToken;
import com.bobsusedbooks.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private EmailService emailService;
    
    @Transactional
    public String createPasswordResetToken(String email) {
        Optional<Customer> customerOpt = customerRepository.findByEmail(email);
        
        if (customerOpt.isEmpty()) {
            throw new RuntimeException("No user found with email: " + email);
        }
        
        Customer customer = customerOpt.get();
        
        // Generate token
        String token = UUID.randomUUID().toString();
        
        // Create token entity
        PasswordResetToken resetToken = new PasswordResetToken(
                token,
                customer,
                LocalDateTime.now().plusHours(24) // Token valid for 24 hours
        );
        
        // Save token
        // passwordResetTokenRepository.save(resetToken);
        
        // Send email with reset link
        String resetLink = "http://localhost:8080/reset-password?token=" + token;
        emailService.sendPasswordResetEmail(customer, resetLink);
        
        return token;
    }
    
    @Transactional
    public String createPasswordResetTokenForCustomer(Customer customer) {
        // Generate token
        String token = UUID.randomUUID().toString();
        
        // Create token entity
        PasswordResetToken resetToken = new PasswordResetToken(
                token,
                customer,
                LocalDateTime.now().plusHours(24) // Token valid for 24 hours
        );
        
        // Save token
        // passwordResetTokenRepository.save(resetToken);
        
        // Send email with reset link
        String resetLink = "http://localhost:8080/reset-password?token=" + token;
        emailService.sendPasswordResetEmail(customer, resetLink);
        
        return token;
    }
    
    @Transactional
    public boolean validatePasswordResetToken(String token) {
        Optional<PasswordResetToken> tokenOpt = findByToken(token);
        
        if (tokenOpt.isEmpty()) {
            return false;
        }
        
        PasswordResetToken resetToken = tokenOpt.get();
        
        // Check if token is expired or used
        if (resetToken.isExpired() || resetToken.getUsed()) {
            return false;
        }
        
        return true;
    }
    
    @Transactional
    public Optional<Customer> findCustomerByToken(String token) {
        Optional<PasswordResetToken> tokenOpt = findByToken(token);
        
        if (tokenOpt.isEmpty()) {
            return Optional.empty();
        }
        
        PasswordResetToken resetToken = tokenOpt.get();
        
        // Check if token is expired or used
        if (resetToken.isExpired() || resetToken.getUsed()) {
            return Optional.empty();
        }
        
        return Optional.of(resetToken.getCustomer());
    }
    
    @Transactional
    public boolean resetPassword(String token, String newPassword) {
        Optional<PasswordResetToken> tokenOpt = findByToken(token);
        
        if (tokenOpt.isEmpty()) {
            return false;
        }
        
        PasswordResetToken resetToken = tokenOpt.get();
        
        // Check if token is expired or used
        if (resetToken.isExpired() || resetToken.getUsed()) {
            return false;
        }
        
        // Update password
        Customer customer = resetToken.getCustomer();
        customer.setPasswordHash(passwordEncoder.encode(newPassword));
        customerRepository.save(customer);
        
        // Mark token as used
        resetToken.setUsed(true);
        // passwordResetTokenRepository.save(resetToken);
        
        return true;
    }
    
    private Optional<PasswordResetToken> findByToken(String token) {
        // Placeholder - implement actual repository method
        return Optional.empty();
    }
}
