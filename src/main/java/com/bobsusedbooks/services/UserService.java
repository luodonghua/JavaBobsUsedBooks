package com.bobsusedbooks.services;

import com.bobsusedbooks.dtos.CustomerDto;
import com.bobsusedbooks.dtos.RegisterUserDto;
import com.bobsusedbooks.entities.Customer;
import com.bobsusedbooks.mappers.CustomerMapper;
import com.bobsusedbooks.repositories.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private CustomerMapper customerMapper;
    
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
    
    public Optional<Customer> findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }
    
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }
    
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
    
    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }
    
    public boolean existsByUsername(String username) {
        return customerRepository.existsByUsername(username);
    }
    
    public boolean emailExists(String email) {
        return customerRepository.existsByEmail(email);
    }
    
    public boolean usernameExists(String username) {
        return customerRepository.existsByUsername(username);
    }
    
    public CustomerDto registerNewUser(@Valid RegisterUserDto registerDto) {
        // Check if username or email already exists
        if (customerRepository.existsByUsername(registerDto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        
        if (customerRepository.existsByEmail(registerDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        // Create new customer
        Customer customer = new Customer();
        customer.setUsername(registerDto.getUsername());
        customer.setEmail(registerDto.getEmail());
        customer.setFirstName(registerDto.getFirstName());
        customer.setLastName(registerDto.getLastName());
        customer.setPhoneNumber(registerDto.getPhoneNumber());
        customer.setSub(registerDto.getUsername()); // Using username as sub for simplicity
        customer.setPasswordHash(passwordEncoder.encode(registerDto.getPassword()));
        customer.setCreatedOn(LocalDateTime.now());
        // Set email as verified for demo purposes - no email verification needed
        customer.setEmailVerified(true);
        customer.setRole("USER");
        
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDto(savedCustomer);
    }
    
    public CustomerDto mapToUserDto(Customer customer) {
        return customerMapper.toDto(customer);
    }
    
    public boolean resetPassword(String email, String newPassword) {
        Optional<Customer> customerOpt = customerRepository.findByEmail(email);
        
        if (customerOpt.isEmpty()) {
            return false;
        }
        
        Customer customer = customerOpt.get();
        customer.setPasswordHash(passwordEncoder.encode(newPassword));
        customerRepository.save(customer);
        
        return true;
    }
}
