package com.bobsusedbooks.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "order_id")
    private Long orderId;
    
    @Column(name = "book_id")
    private Long bookId;
    private Integer quantity;
    
    @Column(name = "price")
    private BigDecimal price;
    
    @Column(name = "book_price", nullable = false)
    private BigDecimal bookPrice;
    
    private BigDecimal discount;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Book book;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;
    
    public OrderItem(Long orderId, Long bookId, Integer quantity, BigDecimal price) {
        this.orderId = orderId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.price = price;
        this.bookPrice = price; // Set book_price to the same value as price
        this.discount = BigDecimal.ZERO;
    }
    
    public BigDecimal getTotalPrice() {
        return price.multiply(new BigDecimal(quantity)).subtract(discount);
    }
    
    public Order getOrder() {
        return order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
        if (order != null) {
            this.orderId = order.getId();
        }
    }
    
    // Ensure book_price is always set when price is set
    public void setPrice(BigDecimal price) {
        this.price = price;
        if (this.bookPrice == null) {
            this.bookPrice = price;
        }
    }
}
