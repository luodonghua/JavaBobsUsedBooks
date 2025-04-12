package com.bobsusedbooks.entities;

import com.bobsusedbooks.common.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@jakarta.persistence.Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
public class Author extends Entity {
    
    private String name;
    
    private LocalDate birthDate;
    
    private String biography;
    
    private String imageUrl;
    
    public Author(String name, LocalDate birthDate, String biography, String imageUrl) {
        this.name = name;
        this.birthDate = birthDate;
        this.biography = biography;
        this.imageUrl = imageUrl;
    }
}
