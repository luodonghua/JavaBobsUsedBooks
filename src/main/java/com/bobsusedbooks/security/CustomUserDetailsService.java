package com.bobsusedbooks.security;

import com.bobsusedbooks.entities.Customer;
import com.bobsusedbooks.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customerOpt = customerRepository.findByUsername(username);
        
        if (customerOpt.isEmpty()) {
            // Try with email
            customerOpt = customerRepository.findByEmail(username);
            
            if (customerOpt.isEmpty()) {
                throw new UsernameNotFoundException("User not found with username or email: " + username);
            }
        }
        
        Customer customer = customerOpt.get();
        
        // Create authorities
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + customer.getRole()));
        
        // Create UserDetails with null-safe handling
        UserDetails userDetails = User.builder()
                .username(customer.getUsername())
                .password(customer.getPasswordHash())
                .authorities(authorities)
                .accountExpired(customer.getAccountExpired() != null ? customer.getAccountExpired() : false)
                .credentialsExpired(customer.getCredentialsExpired() != null ? customer.getCredentialsExpired() : false)
                .disabled(customer.getEmailVerified() != null ? !customer.getEmailVerified() : false)
                .accountLocked(customer.getAccountLocked() != null ? customer.getAccountLocked() : false)
                .build();
        
        // Update last login time
        updateLastLogin(customer);
        
        return userDetails;
    }
    
    @Transactional
    public void updateLastLogin(Customer customer) {
        customer.setLastLogin(LocalDateTime.now());
        customerRepository.save(customer);
    }
}
