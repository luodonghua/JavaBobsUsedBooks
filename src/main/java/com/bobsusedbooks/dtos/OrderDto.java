package com.bobsusedbooks.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Long customerId;
    private String customerName;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private Integer orderStatus;
    private String status;
    private String trackingNumber;
    private String notes;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private LocalDate shippedDate;
    private LocalDate deliveredDate;
    private LocalDate cancelledDate;
    private String cancellationReason;
    private Long addressId;
    private String shippingAddress;
    private String addressSummary;
    
    private BigDecimal subtotal = BigDecimal.ZERO;
    private BigDecimal shippingCost = new BigDecimal("5.00");
    private BigDecimal tax = BigDecimal.ZERO;
    private BigDecimal total = BigDecimal.ZERO;
    
    private List<OrderItemDto> items = new ArrayList<>();
    
    public String getOrderStatusText() {
        return status;
    }
    
    public void setOrderStatusText(String statusText) {
        this.status = statusText;
    }
    
    public List<OrderItemDto> getOrderItems() {
        return items;
    }
    
    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.items = orderItems;
    }
    
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }
    
    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
