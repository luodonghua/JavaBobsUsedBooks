package com.bobsusedbooks.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private String createdBy;
    private String role;
    private Boolean accountLocked;
    
    // Get full name
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    // Check if user is admin
    public boolean isAdmin() {
        return "ADMIN".equals(role);
    }
}
