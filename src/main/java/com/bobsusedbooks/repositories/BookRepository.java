package com.bobsusedbooks.repositories;

import com.bobsusedbooks.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    // Basic queries using Spring Data JPA method naming conventions
    List<Book> findByGenreId(Long genreId);
    
    Page<Book> findByGenreId(Long genreId, Pageable pageable);
    
    List<Book> findByNameContainingIgnoreCaseOrAuthorContainingIgnoreCase(String name, String author);
    
    Page<Book> findByNameContainingIgnoreCaseOrAuthorContainingIgnoreCase(String name, String author, Pageable pageable);
    
    List<Book> findByIsFeaturedTrue();
    
    List<Book> findByIsBestsellerTrue();
    
    List<Book> findByIsNewArrivalTrue();
    
    List<Book> findBySellerId(Long sellerId);
    
    List<Book> findByIsAvailableTrue();
    
    // Method needed by SellController
    List<Book> findByIsUserListingTrueAndIsAvailableTrueAndCreatedBy(String createdBy);
    
    // Inventory report queries using JPQL
    @Query("SELECT COUNT(b) FROM Book b")
    Integer countTotalBooks();
    
    @Query("SELECT SUM(b.price * b.quantity) FROM Book b")
    BigDecimal calculateTotalInventoryValue();
    
    @Query("SELECT b.genre.name, COUNT(b) FROM Book b GROUP BY b.genre.name ORDER BY COUNT(b) DESC")
    List<Object[]> countBooksByGenre();
    
    @Query("SELECT b.genre.name, SUM(b.price * b.quantity) FROM Book b GROUP BY b.genre.name ORDER BY SUM(b.price * b.quantity) DESC")
    List<Object[]> calculateInventoryValueByGenre();
    
    @Query("SELECT b FROM Book b WHERE b.quantity < :threshold ORDER BY b.quantity ASC")
    List<Book> findBooksWithLowStock(@Param("threshold") int threshold);
    
    // Find books created by specific users (for admin/offers)
    List<Book> findByCreatedByIn(Collection<String> createdBy);
    
    Page<Book> findByCreatedByIn(Collection<String> createdBy, Pageable pageable);
    
    // Find books NOT created by specific users (for customer books)
    List<Book> findByCreatedByNotIn(Collection<String> createdBy);
    
    Page<Book> findByCreatedByNotIn(Collection<String> createdBy, Pageable pageable);
    
    // Advanced search query using native SQL to avoid LOWER() function issues with bytea
       @Query(value = "SELECT * FROM books b WHERE " +
              "(:keyword IS NULL OR UPPER(b.name) LIKE UPPER('%' || :keyword || '%') OR UPPER(b.author) LIKE UPPER('%' || :keyword || '%')) AND " +
              "(:author IS NULL OR UPPER(b.author) LIKE UPPER('%' || :author || '%')) AND " +
              "(:isbn IS NULL OR b.isbn LIKE '%' || :isbn || '%') AND " +
              "(:publisherId IS NULL OR b.publisher_id = :publisherId) AND " +
              "(:conditionId IS NULL OR b.condition_id = :conditionId) AND " +
              "(:bookTypeId IS NULL OR b.book_type_id = :bookTypeId) AND " +
              "(:genreId IS NULL OR b.genre_id = :genreId) AND " +
              "(:minPrice IS NULL OR b.price >= :minPrice) AND " +
              "(:maxPrice IS NULL OR b.price <= :maxPrice)",
              countQuery = "SELECT COUNT(*) FROM books b WHERE " +
              "(:keyword IS NULL OR UPPER(b.name) LIKE UPPER('%' || :keyword || '%') OR UPPER(b.author) LIKE UPPER('%' || :keyword || '%')) AND " +
              "(:author IS NULL OR UPPER(b.author) LIKE UPPER('%' || :author || '%')) AND " +
              "(:isbn IS NULL OR b.isbn LIKE '%' || :isbn || '%') AND " +
              "(:publisherId IS NULL OR b.publisher_id = :publisherId) AND " +
              "(:conditionId IS NULL OR b.condition_id = :conditionId) AND " +
              "(:bookTypeId IS NULL OR b.book_type_id = :bookTypeId) AND " +
              "(:genreId IS NULL OR b.genre_id = :genreId) AND " +
              "(:minPrice IS NULL OR b.price >= :minPrice) AND " +
              "(:maxPrice IS NULL OR b.price <= :maxPrice)",
              nativeQuery = true)
    Page<Book> findByAdvancedSearch(
            @Param("keyword") String keyword,
            @Param("author") String author,
            @Param("isbn") String isbn,
            @Param("publisherId") Long publisherId,
            @Param("conditionId") Long conditionId,
            @Param("bookTypeId") Long bookTypeId,
            @Param("genreId") Long genreId,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            Pageable pageable);

    @Query(value = 
            "SELECT b.* FROM BOOKS b " +
            "WHERE CONTAINS(search_text, ?1, 1) > 0 " +
            "ORDER BY SCORE(1) DESC",
            countQuery = "SELECT COUNT(*) FROM BOOKS b WHERE CONTAINS(search_text, ?1) > 0",
            nativeQuery = true)
    List<Book> fullTextSearchBooks(String searchTerms);        
}
