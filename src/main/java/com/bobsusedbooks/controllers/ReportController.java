package com.bobsusedbooks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bobsusedbooks.services.BookService;
import com.bobsusedbooks.services.OrderService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/reports")
public class ReportController {

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private BookService bookService;
    
    @GetMapping("/monthly-sales")
    public String monthlySalesReport(@RequestParam(required = false) String month, Model model) {
        // Default to current month if not specified
        YearMonth yearMonth;
        if (month == null || month.isEmpty()) {
            yearMonth = YearMonth.now();
        } else {
            try {
                yearMonth = YearMonth.parse(month, DateTimeFormatter.ofPattern("yyyy-MM"));
            } catch (Exception e) {
                yearMonth = YearMonth.now();
            }
        }
        
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        
        // Get sales data
        double totalSales = orderService.getTotalSalesForPeriod(startDate, endDate);
        int orderCount = orderService.getOrderCountForPeriod(startDate, endDate);
        double averageOrderValue = orderCount > 0 ? totalSales / orderCount : 0;
        
        // Daily sales breakdown
        Map<LocalDate, Double> dailySales = orderService.getDailySalesForPeriod(startDate, endDate);
        
        // Sales by category
        Map<String, Double> salesByCategory = orderService.getSalesByCategoryForPeriod(startDate, endDate);
        
        // Add data to model
        model.addAttribute("yearMonth", yearMonth);
        model.addAttribute("totalSales", totalSales);
        model.addAttribute("orderCount", orderCount);
        model.addAttribute("averageOrderValue", averageOrderValue);
        model.addAttribute("dailySales", dailySales);
        model.addAttribute("salesByCategory", salesByCategory);
        model.addAttribute("formattedMonth", yearMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy")));
        
        return "admin/reports/monthly-sales";
    }
    
    @GetMapping("/inventory")
    public String inventoryReport(Model model) {
        // Get inventory data
        int totalBooks = bookService.getTotalBookCount();
        double totalValue = bookService.getTotalInventoryValue();
        
        // Books by category
        Map<String, Integer> booksByCategory = bookService.getBookCountByCategory();
        
        // Low stock items (less than 5 in stock)
        model.addAttribute("lowStockBooks", bookService.getLowStockBooks(5));
        
        // Inventory value by category
        Map<String, Double> valueByCategory = bookService.getInventoryValueByCategory();
        
        // Calculate percentages for display
        Map<String, Double> booksByCategoryPercentage = new HashMap<>();
        Map<String, Double> valuesByCategoryPercentage = new HashMap<>();
        
        if (totalBooks > 0) {
            for (Map.Entry<String, Integer> entry : booksByCategory.entrySet()) {
                double percentage = (entry.getValue() * 100.0) / totalBooks;
                booksByCategoryPercentage.put(entry.getKey(), percentage);
            }
        }
        
        if (totalValue > 0) {
            for (Map.Entry<String, Double> entry : valueByCategory.entrySet()) {
                // Debug output
                System.out.println("Category: " + entry.getKey() + 
                                  ", Value: " + entry.getValue() + 
                                  ", Total: " + totalValue + 
                                  ", Percentage: " + ((entry.getValue() * 100.0) / totalValue));
                
                double percentage = (entry.getValue() * 100.0) / totalValue;
                valuesByCategoryPercentage.put(entry.getKey(), percentage);
            }
        }
        
        // Add data to model
        model.addAttribute("totalBooks", totalBooks);
        model.addAttribute("totalValue", totalValue);
        model.addAttribute("booksByCategory", booksByCategory);
        model.addAttribute("valueByCategory", valueByCategory);
        model.addAttribute("booksByCategoryPercentage", booksByCategoryPercentage);
        model.addAttribute("valuesByCategoryPercentage", valuesByCategoryPercentage);
        
        return "admin/reports/inventory";
    }
}
