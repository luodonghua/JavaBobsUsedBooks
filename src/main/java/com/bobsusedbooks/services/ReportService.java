package com.bobsusedbooks.services;

import com.bobsusedbooks.dtos.MonthlySalesReportDto;
import com.bobsusedbooks.dtos.ReportItemDto;
import com.bobsusedbooks.entities.Book;
import com.bobsusedbooks.entities.Order;
import com.bobsusedbooks.entities.OrderItem;
import com.bobsusedbooks.repositories.BookRepository;
import com.bobsusedbooks.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    public MonthlySalesReportDto generateMonthlySalesReport(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        
        // Get orders for the month
        List<Order> orders = orderRepository.findByOrderDateBetween(startDate, endDate);
        
        // Initialize report data
        BigDecimal totalSales = BigDecimal.ZERO;
        int totalItems = 0;
        int totalOrders = orders.size();
        Map<LocalDate, BigDecimal> dailySales = new HashMap<>();
        Map<String, BigDecimal> salesByCategory = new HashMap<>();
        List<ReportItemDto> topSellingBooks = new ArrayList<>();
        
        // Process orders
        for (Order order : orders) {
            LocalDate orderDate = order.getOrderDate();
            BigDecimal orderTotal = order.getTotalAmount();
            
            // Add to total sales
            totalSales = totalSales.add(orderTotal);
            
            // Add to daily sales
            dailySales.put(orderDate, dailySales.getOrDefault(orderDate, BigDecimal.ZERO).add(orderTotal));
            
            // Process order items
            for (OrderItem item : order.getOrderItems()) {
                totalItems += item.getQuantity();
                
                // Add to sales by category
                if (item.getBook() != null && item.getBook().getGenre() != null) {
                    String category = item.getBook().getGenre();
                    BigDecimal itemTotal = item.getPrice().multiply(new BigDecimal(item.getQuantity()));
                    salesByCategory.put(category, salesByCategory.getOrDefault(category, BigDecimal.ZERO).add(itemTotal));
                }
                
                // Add to top selling books
                if (item.getBook() != null) {
                    ReportItemDto bookItem = new ReportItemDto();
                    bookItem.setId(item.getBook().getId());
                    bookItem.setName(item.getBook().getName());
                    bookItem.setAuthor(item.getBook().getAuthor());
                    bookItem.setQuantity(item.getQuantity());
                    bookItem.setPrice(item.getBookPrice());
                    bookItem.setTotal(item.getBookPrice().multiply(new BigDecimal(item.getQuantity())));
                    topSellingBooks.add(bookItem);
                }
            }
        }
        
        // Sort and limit top selling books
        topSellingBooks = topSellingBooks.stream()
                .collect(Collectors.groupingBy(ReportItemDto::getId))
                .values().stream()
                .map(list -> {
                    ReportItemDto combined = new ReportItemDto();
                    combined.setId(list.get(0).getId());
                    combined.setName(list.get(0).getName());
                    combined.setAuthor(list.get(0).getAuthor());
                    combined.setQuantity(list.stream().mapToInt(ReportItemDto::getQuantity).sum());
                    combined.setPrice(list.get(0).getPrice());
                    BigDecimal total = list.stream()
                            .map(ReportItemDto::getTotal)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    combined.setTotal(total);
                    return combined;
                })
                .sorted(Comparator.comparing(ReportItemDto::getQuantity).reversed())
                .limit(5)
                .collect(Collectors.toList());
        
        // Create and return the report
        MonthlySalesReportDto report = new MonthlySalesReportDto();
        report.setYearMonth(yearMonth);
        report.setTotalSales(totalSales);
        report.setTotalOrders(totalOrders);
        report.setTotalItems(totalItems);
        report.setDailySales(dailySales);
        report.setSalesByCategory(salesByCategory);
        report.setTopSellingBooks(topSellingBooks);
        
        return report;
    }
    
    public List<ReportItemDto> generateInventoryReport() {
        List<Book> books = bookRepository.findAll();
        Map<String, List<Book>> booksByCategory = books.stream()
                .collect(Collectors.groupingBy(book -> 
                    book.getGenre() != null ? book.getGenre() : "Uncategorized"));
        
        List<ReportItemDto> reportItems = new ArrayList<>();
        
        for (Map.Entry<String, List<Book>> entry : booksByCategory.entrySet()) {
            String category = entry.getKey();
            List<Book> booksInCategory = entry.getValue();
            
            for (Book book : booksInCategory) {
                ReportItemDto item = new ReportItemDto();
                item.setId(book.getId());
                item.setName(book.getName());
                item.setAuthor(book.getAuthor());
                item.setCategory(category);
                item.setQuantity(book.getQuantityAvailable());
                item.setPrice(book.getPrice());
                item.setTotal(book.getPrice().multiply(new BigDecimal(book.getQuantityAvailable())));
                reportItems.add(item);
            }
        }
        
        return reportItems;
    }
    
    public BigDecimal calculateTotalInventoryValue() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> book.getPrice().multiply(new BigDecimal(book.getQuantityAvailable())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
