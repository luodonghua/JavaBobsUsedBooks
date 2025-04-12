package com.bobsusedbooks.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartItemDto {
    private Long id;
    private Long customerId;
    private Long bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookCoverUrl;
    private Integer quantity;
    private BigDecimal bookPrice;
    private Boolean isWishlistItem;
    
    public BigDecimal getTotalPrice() {
        return bookPrice != null ? bookPrice.multiply(new BigDecimal(quantity)) : BigDecimal.ZERO;
    }
}
