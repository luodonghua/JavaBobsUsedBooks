package com.bobsusedbooks.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private LocalDate shippedDate;
    private LocalDate deliveredDate;
    private LocalDate cancelledDate;
    
    @Column(name = "order_status", nullable = false)
    private Integer orderStatus;
    
    private String status;
    private String cancellationReason;
    private String trackingNumber;
    private String notes;
    
    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;
    
    private Long addressId;
    private String shippingAddress;
    private BigDecimal totalAmount;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();
    
    public Order(Long customerId) {
        this.customerId = customerId;
        this.orderDate = LocalDate.now();
        this.orderStatus = 0; // Pending
        this.status = "Pending";
        this.createdOn = LocalDateTime.now();
        this.totalAmount = BigDecimal.ZERO;
    }
    
    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        if (createdOn == null) {
            createdOn = now;
        }
        if (orderStatus == null) {
            orderStatus = 0; // Default to "Pending"
        }
        if (status == null) {
            status = "PENDING";
        }
        if (orderDate == null) {
            orderDate = LocalDate.now();
        }
    }
    
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }
    
    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
    
    public BigDecimal getTotalAmount() {
        if (totalAmount != null) {
            return totalAmount;
        }
        
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : orderItems) {
            total = total.add(item.getTotalPrice());
        }
        return total;
    }
}
