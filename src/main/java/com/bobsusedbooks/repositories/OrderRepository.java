package com.bobsusedbooks.repositories;

import com.bobsusedbooks.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    // Basic queries using Spring Data JPA method naming conventions
    List<Order> findByCustomerIdOrderByCreatedOnDesc(Long customerId);
    
    Page<Order> findByCustomerIdOrderByOrderDateDesc(Long customerId, Pageable pageable);
    
    List<Order> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);
    
    // Sales report queries using JPQL
    @Query("SELECT SUM(o.totalAmount) FROM Order o")
    BigDecimal calculateTotalSales();
    
    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    BigDecimal calculateSalesForPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    @Query("SELECT COUNT(o) FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    Integer countOrdersForPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    @Query("SELECT o.orderDate, SUM(o.totalAmount) FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate GROUP BY o.orderDate ORDER BY o.orderDate")
    List<Object[]> findDailySalesForPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    @Query("SELECT b.genre.name, SUM(oi.price * oi.quantity) FROM Order o JOIN o.orderItems oi JOIN oi.book b WHERE o.orderDate BETWEEN :startDate AND :endDate GROUP BY b.genre.name ORDER BY SUM(oi.price * oi.quantity) DESC")
    List<Object[]> findSalesByCategoryForPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    @Query("SELECT AVG(o.totalAmount) FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    BigDecimal calculateAverageOrderValueForPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    // Status-based queries
    List<Order> findByStatus(String status);
    
    @Query("SELECT COUNT(o) FROM Order o WHERE o.status = :status")
    Integer countOrdersByStatus(@Param("status") String status);
    
    // Recent orders
    @Query("SELECT o FROM Order o ORDER BY o.orderDate DESC")
    List<Order> findRecentOrders(Pageable pageable);
}
