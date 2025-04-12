package com.bobsusedbooks.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlySalesReportDto {
    private YearMonth yearMonth;
    private BigDecimal totalSales = BigDecimal.ZERO;
    private int totalItems = 0;
    private int totalOrders = 0;
    private Map<LocalDate, BigDecimal> dailySales = new HashMap<>();
    private Map<String, BigDecimal> salesByCategory = new HashMap<>();
    private List<ReportItemDto> topSellingBooks = new ArrayList<>();
    
    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }
    
    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
    
    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }
    
    public void setDailySales(Map<LocalDate, BigDecimal> dailySales) {
        this.dailySales = dailySales;
    }
    
    public void setSalesByCategory(Map<String, BigDecimal> salesByCategory) {
        this.salesByCategory = salesByCategory;
    }
    
    public void setTopSellingBooks(List<ReportItemDto> topSellingBooks) {
        this.topSellingBooks = topSellingBooks;
    }
}
