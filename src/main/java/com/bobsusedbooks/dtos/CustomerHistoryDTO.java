package com.bobsusedbooks.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerHistoryDTO {
    private Long orderId;
    private LocalDateTime orderDate;
    private Integer orderStatus;
    private String statusDescription;
    private BigDecimal totalAmount;
    private Long bookId;
    private String bookTitle;
    private String author;
    private String genre;
    private Integer quantity;
    private BigDecimal bookPrice;
    private BigDecimal discount;
    private BigDecimal itemTotal;
}
