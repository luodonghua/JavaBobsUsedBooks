package com.bobsusedbooks.repositories;

import com.bobsusedbooks.entities.ReferenceDataItem;
import com.bobsusedbooks.enums.ReferenceDataType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReferenceDataRepository extends JpaRepository<ReferenceDataItem, Integer> {
    
    List<ReferenceDataItem> findByType(ReferenceDataType type);
    
    List<ReferenceDataItem> findByTypeAndIsActiveTrue(ReferenceDataType type);
    
    Page<ReferenceDataItem> findByTypeAndNameContainingIgnoreCase(ReferenceDataType type, String name, Pageable pageable);
}
