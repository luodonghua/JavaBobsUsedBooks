package com.bobsusedbooks.repositories;

import com.bobsusedbooks.entities.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
    
    List<ShoppingCartItem> findByCustomerId(Long customerId);
    
    List<ShoppingCartItem> findByCustomerIdAndIsWishlistItem(Long customerId, Boolean isWishlistItem);
    
    Optional<ShoppingCartItem> findByCustomerIdAndBookId(Long customerId, Long bookId);
    
    void deleteByCustomerId(Long customerId);
}
