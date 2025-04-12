package com.bobsusedbooks.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto {
    private Long id;
    private String bookName;
    private String bookTitle;
    private String author;
    private String isbn;
    private String description;
    private BigDecimal offeredPrice;
    private BigDecimal bookPrice;
    private Long conditionId;
    private String conditionDescription;
    private Integer status;
    private Integer offerStatus;
    private String offerStatusName;
    private LocalDateTime createdOn;
    private LocalDateTime updatedAt;
    private String bookComment;
    private String frontUrl;
    private String summary;
    
    // Reference data
    private Long genreId;
    private String genreName;
    private Long publisherId;
    private String publisherName;
    private Long bookTypeId;
    private String bookTypeName;
    
    // Customer info
    private Long customerId;
    private String customerName;
}
