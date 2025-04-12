package com.bobsusedbooks.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookStatisticsDto {
    private Long totalBooks;
    private Long availableBooks;
    private Long outOfStockBooks;
}
