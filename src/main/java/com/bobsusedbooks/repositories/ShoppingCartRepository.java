package com.bobsusedbooks.repositories;

import com.bobsusedbooks.entities.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartItem, Long> {
    
    List<ShoppingCartItem> findByCustomerId(Long customerId);
    
    List<ShoppingCartItem> findByCustomerIdAndIsWishlistItem(Long customerId, Boolean isWishlistItem);
    
    Optional<ShoppingCartItem> findByCustomerIdAndBookId(Long customerId, Long bookId);
    
    @Modifying
    @Query("DELETE FROM ShoppingCartItem sci WHERE sci.customerId = :customerId AND sci.bookId = :bookId")
    void deleteByCustomerIdAndBookId(@Param("customerId") Long customerId, @Param("bookId") Long bookId);
    
    @Modifying
    @Query("DELETE FROM ShoppingCartItem sci WHERE sci.customerId = :customerId")
    void deleteByCustomerId(@Param("customerId") Long customerId);
    
    @Modifying
    @Query("DELETE FROM ShoppingCartItem sci WHERE sci.customerId = :customerId AND sci.isWishlistItem = :isWishlistItem")
    void deleteByCustomerIdAndIsWishlistItem(@Param("customerId") Long customerId, @Param("isWishlistItem") Boolean isWishlistItem);
}
