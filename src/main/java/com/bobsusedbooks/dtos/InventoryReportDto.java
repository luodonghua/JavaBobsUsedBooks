package com.bobsusedbooks.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryReportDto {
    private LocalDate reportDate;
    private int totalBooks = 0;
    private BigDecimal totalValue = BigDecimal.ZERO;
    private Map<String, Integer> booksByCategory = new HashMap<>();
    private List<ReportItemDto> inventoryItems = new ArrayList<>();
    
    public void setTotalBooks(int totalBooks) {
        this.totalBooks = totalBooks;
    }
    
    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }
    
    public void setBooksByCategory(Map<String, Integer> booksByCategory) {
        this.booksByCategory = booksByCategory;
    }
    
    public void setInventoryItems(List<ReportItemDto> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }
}
