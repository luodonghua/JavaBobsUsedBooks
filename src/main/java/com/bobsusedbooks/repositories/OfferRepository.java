package com.bobsusedbooks.repositories;

import com.bobsusedbooks.entities.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    
    List<Offer> findByCustomerId(Long customerId);
    
    Page<Offer> findByCustomerId(Long customerId, Pageable pageable);
    
    List<Offer> findByStatus(Integer status);
    
    List<Offer> findByOfferStatus(Integer offerStatus);
    
    Page<Offer> findByOfferStatus(Integer offerStatus, Pageable pageable);
}
