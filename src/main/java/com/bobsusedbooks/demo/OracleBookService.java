/**
 * This service class demonstrates Oracle-specific SQL statements embedded in Java code.
 * It's designed to showcase Amazon Q's SQL conversion capabilities using the /transform command.
 * 
 * IMPORTANT: This class contains Oracle-specific SQL syntax that can be transformed
 * to PostgreSQL using Amazon Q's /transform command.
 */


package com.bobsusedbooks.demo;

import com.bobsusedbooks.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class OracleBookService {

    @Autowired
    private DataSource dataSource;
    
    /**
     * Get book recommendations based on purchase history using Oracle-specific SQL
     */
    public List<Book> getBookRecommendations(Long customerId) throws SQLException {
        List<Book> recommendations = new ArrayList<>();
        
        // Oracle-specific SQL with hierarchical query, analytic functions, and Oracle date functions
        String sql = 
            "WITH customer_genres AS (" +
            "  SELECT b.genre_id, COUNT(*) as purchase_count " +
            "  FROM orders o " +
            "  JOIN order_items oi ON o.id = oi.order_id " +
            "  JOIN books b ON oi.book_id = b.id " +
            "  WHERE o.customer_id = ? " +
            "  AND o.order_date > ADD_MONTHS(SYSDATE, -6) " +
            "  GROUP BY b.genre_id " +
            "  ORDER BY purchase_count DESC " +
            "), " +
            "purchased_books AS (" +
            "  SELECT b.id " +
            "  FROM orders o " +
            "  JOIN order_items oi ON o.id = oi.order_id " +
            "  JOIN books b ON oi.book_id = b.id " +
            "  WHERE o.customer_id = ? " +
            "), " +
            "ranked_recommendations AS (" +
            "  SELECT b.*, " +
            "         ROW_NUMBER() OVER (PARTITION BY b.genre_id ORDER BY b.average_rating DESC NULLS LAST) as genre_rank " +
            "  FROM books b " +
            "  JOIN customer_genres cg ON b.genre_id = cg.genre_id " +
            "  WHERE b.id NOT IN (SELECT id FROM purchased_books) " +
            "  AND b.is_available = 1 " +
            "  AND b.quantity > 0 " +
            ") " +
            "SELECT * FROM ranked_recommendations " +
            "WHERE genre_rank <= 3 " +
            "ORDER BY (SELECT purchase_count FROM customer_genres cg WHERE cg.genre_id = ranked_recommendations.genre_id) DESC, " +
            "genre_rank ASC";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, customerId);
            stmt.setLong(2, customerId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Book book = mapRowToBook(rs);
                recommendations.add(book);
            }
        }
        
        return recommendations;
    }
    
    /**
     * Get sales report by region using Oracle-specific SQL
     */
    public List<Map<String, Object>> getSalesByRegion(Date startDate, Date endDate) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<>();
        
        // Oracle-specific SQL with PIVOT, TO_CHAR, and CONNECT BY
        String sql = 
            "SELECT * FROM (" +
            "  SELECT " +
            "    r.name as region, " +
            "    TO_CHAR(o.order_date, 'YYYY-MM') as month, " +
            "    SUM(oi.quantity * oi.price) as total_sales " +
            "  FROM orders o " +
            "  JOIN customers c ON o.customer_id = c.id " +
            "  JOIN addresses a ON c.address_id = a.id " +
            "  JOIN regions r ON a.region_id = r.id " +
            "  JOIN order_items oi ON o.id = oi.order_id " +
            "  WHERE o.order_date BETWEEN ? AND ? " +
            "  CONNECT BY PRIOR r.parent_id = r.id " +
            "  START WITH r.parent_id IS NULL " +
            "  GROUP BY r.name, TO_CHAR(o.order_date, 'YYYY-MM') " +
            ") " +
            "PIVOT ( " +
            "  SUM(total_sales) " +
            "  FOR month IN (" +
            "    SELECT DISTINCT TO_CHAR(ADD_MONTHS(?, LEVEL-1), 'YYYY-MM') as month " +
            "    FROM dual " +
            "    CONNECT BY LEVEL <= MONTHS_BETWEEN(?, ?) + 1" +
            "  ) " +
            ") " +
            "ORDER BY region";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, startDate);
            stmt.setDate(2, endDate);
            stmt.setDate(3, startDate);
            stmt.setDate(4, endDate);
            stmt.setDate(5, startDate);
            ResultSet rs = stmt.executeQuery();
            
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), rs.getObject(i));
                }
                results.add(row);
            }
        }
        
        return results;
    }
    
    /**
     * Get inventory aging report using Oracle-specific SQL
     */
    public List<Map<String, Object>> getInventoryAgingReport() throws SQLException {
        List<Map<String, Object>> results = new ArrayList<>();
        
        // Oracle-specific SQL with CASE expressions, date arithmetic, and NVL
        String sql = 
            "SELECT " +
            "  g.name as genre, " +
            "  COUNT(*) as total_books, " +
            "  SUM(b.quantity) as total_quantity, " +
            "  SUM(b.price * b.quantity) as total_value, " +
            "  SUM(CASE " +
            "    WHEN b.created_date > SYSDATE - 30 THEN b.quantity " +
            "    ELSE 0 " +
            "  END) as qty_0_30_days, " +
            "  SUM(CASE " +
            "    WHEN b.created_date BETWEEN SYSDATE - 90 AND SYSDATE - 31 THEN b.quantity " +
            "    ELSE 0 " +
            "  END) as qty_31_90_days, " +
            "  SUM(CASE " +
            "    WHEN b.created_date BETWEEN SYSDATE - 180 AND SYSDATE - 91 THEN b.quantity " +
            "    ELSE 0 " +
            "  END) as qty_91_180_days, " +
            "  SUM(CASE " +
            "    WHEN b.created_date < SYSDATE - 180 THEN b.quantity " +
            "    ELSE 0 " +
            "  END) as qty_over_180_days, " +
            "  NVL(AVG(SYSDATE - b.created_date), 0) as avg_days_in_inventory " +
            "FROM books b " +
            "JOIN genres g ON b.genre_id = g.id " +
            "WHERE b.quantity > 0 " +
            "GROUP BY g.name " +
            "ORDER BY total_value DESC";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnLabel(i), rs.getObject(i));
                }
                results.add(row);
            }
        }
        
        return results;
    }
    
    /**
     * Search books with full text search using Oracle Text
     */
    public List<Book> fullTextSearch(String searchTerms) throws SQLException {
        List<Book> books = new ArrayList<>();
        
        // Oracle-specific SQL with Oracle Text (CONTAINS) and SCORE
        String sql = 
            "SELECT /*+ FIRST_ROWS(10) */ " +
            "  b.*, " +
            "  SCORE(1) as relevance " +
            "FROM books b " +
            "WHERE CONTAINS(b.search_text, ?, 1) > 0 " +
            "ORDER BY SCORE(1) DESC";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, searchTerms);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Book book = mapRowToBook(rs);
                books.add(book);
            }
        }
        
        return books;
    }
    
    /**
     * Get customer purchase patterns using Oracle-specific SQL
     */
    public List<Map<String, Object>> getCustomerPurchasePatterns(Long customerId) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<>();
        
        // Oracle-specific SQL with analytic functions, LISTAGG, and Oracle date functions
        String sql = 
            "SELECT " +
            "  c.id as customer_id, " +
            "  c.name as customer_name, " +
            "  COUNT(DISTINCT o.id) as total_orders, " +
            "  SUM(o.total_amount) as total_spent, " +
            "  AVG(o.total_amount) as avg_order_value, " +
            "  MAX(o.order_date) as last_order_date, " +
            "  ROUND(AVG(LEAD(o.order_date) OVER (PARTITION BY o.customer_id ORDER BY o.order_date) - o.order_date)) as avg_days_between_orders, " +
            "  LISTAGG(DISTINCT g.name, ', ') WITHIN GROUP (ORDER BY COUNT(*) DESC) as top_genres, " +
            "  ROUND(MONTHS_BETWEEN(SYSDATE, MIN(o.order_date))) as customer_age_months " +
            "FROM customers c " +
            "JOIN orders o ON c.id = o.customer_id " +
            "JOIN order_items oi ON o.id = oi.order_id " +
            "JOIN books b ON oi.book_id = b.id " +
            "JOIN genres g ON b.genre_id = g.id " +
            "WHERE c.id = ? " +
            "GROUP BY c.id, c.name";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, customerId);
            ResultSet rs = stmt.executeQuery();
            
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnLabel(i), rs.getObject(i));
                }
                results.add(row);
            }
        }
        
        return results;
    }
    
    /**
     * Get books with similar characteristics using Oracle-specific SQL
     */
    public List<Book> getSimilarBooks(Long bookId, int limit) throws SQLException {
        List<Book> books = new ArrayList<>();
        
        // Oracle-specific SQL with hierarchical query and Oracle-specific functions
        String sql = 
            "WITH book_attributes AS (" +
            "  SELECT " +
            "    b.genre_id, " +
            "    b.author, " +
            "    b.publisher_id, " +
            "    REGEXP_SUBSTR(b.name, '^[^:]+') as series_name " +
            "  FROM books b " +
            "  WHERE b.id = ? " +
            "), " +
            "similar_books AS (" +
            "  SELECT " +
            "    b.*, " +
            "    CASE " +
            "      WHEN b.genre_id = (SELECT genre_id FROM book_attributes) THEN 3 " +
            "      ELSE 0 " +
            "    END + " +
            "    CASE " +
            "      WHEN b.author = (SELECT author FROM book_attributes) THEN 5 " +
            "      ELSE 0 " +
            "    END + " +
            "    CASE " +
            "      WHEN b.publisher_id = (SELECT publisher_id FROM book_attributes) THEN 2 " +
            "      ELSE 0 " +
            "    END + " +
            "    CASE " +
            "      WHEN REGEXP_SUBSTR(b.name, '^[^:]+') = (SELECT series_name FROM book_attributes) THEN 4 " +
            "      ELSE 0 " +
            "    END as similarity_score " +
            "  FROM books b " +
            "  WHERE b.id != ? " +
            "  AND b.is_available = 1 " +
            "  AND b.quantity > 0 " +
            "  CONNECT BY PRIOR b.genre_id = b.genre_id " +
            "  START WITH b.id = ? " +
            ") " +
            "SELECT * FROM similar_books " +
            "WHERE similarity_score > 0 " +
            "ORDER BY similarity_score DESC, average_rating DESC NULLS LAST " +
            "FETCH FIRST ? ROWS ONLY";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, bookId);
            stmt.setLong(2, bookId);
            stmt.setLong(3, bookId);
            stmt.setInt(4, limit);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Book book = mapRowToBook(rs);
                books.add(book);
            }
        }
        
        return books;
    }
    
    /**
     * Helper method to map a ResultSet row to a Book object
     */
    private Book mapRowToBook(ResultSet rs) throws SQLException {
        // Implementation would map ResultSet to Book entity
        // This is simplified for the example
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setIsbn(rs.getString("isbn"));
        book.setPrice(rs.getBigDecimal("price"));
        book.setQuantity(rs.getInt("quantity"));
        // Set other properties...
        
        return book;
    }
}
