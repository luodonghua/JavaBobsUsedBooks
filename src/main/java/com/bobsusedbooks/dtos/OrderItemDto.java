package com.bobsusedbooks.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Long id;
    private Long orderId;
    private Long bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookCoverUrl;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal price;  // Added for compatibility
    private BigDecimal bookPrice; // Added for database compatibility
    private BigDecimal discount; // Added for compatibility
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    
    public BigDecimal getSubtotal() {
        return unitPrice != null ? unitPrice.multiply(new BigDecimal(quantity)) : BigDecimal.ZERO;
    }
    
    // Compatibility methods
    public BigDecimal getPrice() {
        return price != null ? price : unitPrice;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
        this.unitPrice = price;
        this.bookPrice = price; // Ensure bookPrice is also set
    }
    
    public BigDecimal getBookPrice() {
        return bookPrice != null ? bookPrice : price;
    }
    
    public void setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice;
    }
    
    public BigDecimal getDiscount() {
        return discount != null ? discount : BigDecimal.ZERO;
    }
    
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
