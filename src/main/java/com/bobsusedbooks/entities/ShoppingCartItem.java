package com.bobsusedbooks.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shopping_cart_items")
@Getter
@Setter
@NoArgsConstructor
public class ShoppingCartItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "customer_id")
    private Long customerId;
    
    @Column(name = "book_id")
    private Long bookId;
    private Integer quantity;
    private Boolean isWishlistItem;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Book book;
    
    public ShoppingCartItem(Long customerId, Long bookId, Integer quantity) {
        this.customerId = customerId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.isWishlistItem = false;
    }
}
