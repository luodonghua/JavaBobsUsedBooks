package com.bobsusedbooks.entities;

import com.bobsusedbooks.enums.ReferenceDataType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@jakarta.persistence.Entity
@Table(name = "reference_data")
@Getter
@Setter
@NoArgsConstructor
public class ReferenceDataItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String createdBy = "System";

    private LocalDateTime createdOn = LocalDateTime.now();

    private LocalDateTime updatedOn = LocalDateTime.now();

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private ReferenceDataType type;

    private boolean isActive = true;

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

    public ReferenceDataItem(String name, String description, ReferenceDataType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }
}
