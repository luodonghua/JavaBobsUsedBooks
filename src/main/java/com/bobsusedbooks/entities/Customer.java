package com.bobsusedbooks.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String passwordHash;
    private String sub;
    private LocalDate dateOfBirth;
    private LocalDateTime createdOn;
    private Boolean emailVerified;
    private String role;
    private Boolean accountExpired = false;
    private Boolean credentialsExpired = false;
    private Boolean accountLocked = false;
    private LocalDateTime lastLogin;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();
    
    public Customer(String username, String email, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdOn = LocalDateTime.now();
        this.emailVerified = false;
        this.role = "USER";
        this.accountExpired = false;
        this.credentialsExpired = false;
        this.accountLocked = false;
    }
    
    public Boolean getEmailVerified() {
        return emailVerified != null ? emailVerified : false;
    }
    
    public Boolean getAccountExpired() {
        return accountExpired != null ? accountExpired : false;
    }
    
    public Boolean getCredentialsExpired() {
        return credentialsExpired != null ? credentialsExpired : false;
    }
    
    public Boolean getAccountLocked() {
        return accountLocked != null ? accountLocked : false;
    }
    
    public List<Address> getAddresses() {
        return addresses;
    }
}
