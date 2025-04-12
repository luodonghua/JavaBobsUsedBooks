package com.bobsusedbooks.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@jakarta.persistence.Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
public class Author {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String createdBy = "System";
    private LocalDateTime createdOn = LocalDateTime.now();
    private LocalDateTime updatedOn = LocalDateTime.now();    

    private String name;
    
    private LocalDate birthDate;
    
    private String biography;
    
    private String imageUrl;
    
    public boolean isNewEntity() {
        return id == null || id == 0;
    }

    // Backward compatibility methods
    @Deprecated
    public LocalDateTime getcreatedOn() {
        return createdOn;
    }

    @Deprecated
        public void setcreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }


    public Author(String name, LocalDate birthDate, String biography, String imageUrl) {
        this.name = name;
        this.birthDate = birthDate;
        this.biography = biography;
        this.imageUrl = imageUrl;
    }
}
