package com.bobsusedbooks.entities;

import com.bobsusedbooks.common.Entity;
import com.bobsusedbooks.enums.ReferenceDataType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@jakarta.persistence.Entity
@Table(name = "reference_data")
@Getter
@Setter
@NoArgsConstructor
public class ReferenceDataItem extends Entity {
    
    private String name;
    
    private String description;
    
    @Enumerated(EnumType.STRING)
    private ReferenceDataType type;
    
    private boolean isActive = true;
    
    public ReferenceDataItem(String name, String description, ReferenceDataType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }
}
