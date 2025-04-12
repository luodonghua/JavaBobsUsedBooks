package com.bobsusedbooks.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class Entity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String createdBy = "System";
    
    private LocalDateTime createdOn = LocalDateTime.now();
    
    private LocalDateTime updatedOn = LocalDateTime.now();
    
    // @Version
    // private Long rowVersion;
    
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
}
