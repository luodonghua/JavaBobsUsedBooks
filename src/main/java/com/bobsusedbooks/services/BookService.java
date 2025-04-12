package com.bobsusedbooks.services;

import com.bobsusedbooks.dtos.BookDto;
import com.bobsusedbooks.entities.Book;
import com.bobsusedbooks.mappers.BookMapper;
import com.bobsusedbooks.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookMapper bookMapper;
    
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(bookMapper::toDto).collect(Collectors.toList());
    }
    
    public Page<BookDto> getAllBooks(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        return books.map(bookMapper::toDto);
    }
    
    // Method needed by BookController
    public Page<BookDto> getAllBooksPaginated(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        return books.map(bookMapper::toDto);
    }
    
    public Optional<BookDto> getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(bookMapper::toDto);
    }
    
    public Page<BookDto> getCustomerBooksForSale(Pageable pageable) {
        List<String> excludeCreators = List.of("system", "admin");
        Page<Book> books = bookRepository.findByCreatedByNotIn(excludeCreators, pageable);
        return books.map(bookMapper::toDto);
    }
    
    public List<BookDto> getBooksByGenreId(Long genreId) {
        List<Book> books = bookRepository.findByGenreId(genreId);
        return books.stream().map(bookMapper::toDto).collect(Collectors.toList());
    }
    
    public Page<BookDto> getBooksByGenreId(Long genreId, Pageable pageable) {
        Page<Book> books = bookRepository.findByGenreId(genreId, pageable);
        return books.map(bookMapper::toDto);
    }
    
    // Method needed by BookController
    public List<BookDto> getBooksByGenre(Long genreId) {
        return getBooksByGenreId(genreId);
    }
    
    // Method needed by MyListingsController
    public List<BookDto> getBooksBySellerId(Long sellerId) {
        List<Book> books = bookRepository.findBySellerId(sellerId);
        return books.stream().map(bookMapper::toDto).collect(Collectors.toList());
    }
    
    public List<BookDto> searchBooks(String keyword) {
        List<Book> books = bookRepository.findByNameContainingIgnoreCaseOrAuthorContainingIgnoreCase(keyword, keyword);
        return books.stream().map(bookMapper::toDto).collect(Collectors.toList());
    }
    
    public Page<BookDto> searchBooks(String keyword, Pageable pageable) {
        Page<Book> books = bookRepository.findByNameContainingIgnoreCaseOrAuthorContainingIgnoreCase(keyword, keyword, pageable);
        return books.map(bookMapper::toDto);
    }

    public Page<BookDto> fullTextSearchBooksPaginated(String query, Pageable pageable) {
        String searchQuery = prepareSearchQuery(query);
        
        // Get all results
        List<Book> allResults = bookRepository.fullTextSearchBooks(searchQuery);
        
        // Manual pagination
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allResults.size());
        
        List<BookDto> pagedResults = allResults.subList(start, end)
            .stream()
            .map(bookMapper::toDto)
            .collect(Collectors.toList());
            
        return new PageImpl<>(
            pagedResults,
            pageable,
            allResults.size()
        );
    }

    private String prepareSearchQuery(String query) {
        if (query == null || query.trim().isEmpty()) {
            return "%";
        }
        
        String[] terms = query.toLowerCase().split("\\s+");
        return String.join(" OR ", Arrays.stream(terms)
            .map(term -> "fuzzy(" + term + ", 40)")
            .toArray(String[]::new));
    }

    // Method needed by BookController
    public Page<BookDto> searchBooksPaginated(String keyword, Pageable pageable) {
        return searchBooks(keyword, pageable);
    }
    
    @Transactional
    public BookDto createBook(BookDto bookDto) {
        // Set createdOn to current time if not already set
        if (bookDto.getCreatedOn() == null) {
            bookDto.setCreatedOn(java.time.LocalDateTime.now());
        }
        
        // Ensure isAvailable is set to true if null
        if (bookDto.getIsAvailable() == null) {
            bookDto.setIsAvailable(true);
        }
        
        Book book = bookMapper.toEntity(bookDto);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }
    
    @Transactional
    public BookDto updateBook(BookDto bookDto) {
        Optional<Book> existingBookOpt = bookRepository.findById(bookDto.getId());
        
        if (existingBookOpt.isPresent()) {
            Book existingBook = existingBookOpt.get();
            bookMapper.updateEntityFromDto(bookDto, existingBook);
            Book updatedBook = bookRepository.save(existingBook);
            return bookMapper.toDto(updatedBook);
        }
        
        throw new RuntimeException("Book not found with id: " + bookDto.getId());
    }
    
    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    
    public List<BookDto> getFeaturedBooks() {
        List<Book> books = bookRepository.findByIsFeaturedTrue();
        return books.stream().map(bookMapper::toDto).collect(Collectors.toList());
    }
    
    public List<BookDto> getBestsellerBooks() {
        List<Book> books = bookRepository.findByIsBestsellerTrue();
        return books.stream().map(bookMapper::toDto).collect(Collectors.toList());
    }
    
    public List<BookDto> getNewArrivalBooks() {
        List<Book> books = bookRepository.findByIsNewArrivalTrue();
        return books.stream().map(bookMapper::toDto).collect(Collectors.toList());
    }
    
    // Method needed by SellController
    public List<BookDto> getAvailableUserListings(String username) {
        // Find books that are user listings and available for the current user
        List<Book> books = bookRepository.findByIsUserListingTrueAndIsAvailableTrueAndCreatedBy(username);
        return books.stream().map(bookMapper::toDto).collect(Collectors.toList());
    }
    
    // Advanced search method needed by BookController
    public Page<BookDto> findBooks(
            String keyword, String author, String isbn, 
            Long publisherId, Long bookTypeId, Long genreId, Long conditionId, 
            BigDecimal minPrice, BigDecimal maxPrice, 
            Pageable pageable) {
        
        Page<Book> books = bookRepository.findByAdvancedSearch(
            keyword, author, isbn, publisherId, conditionId, bookTypeId, genreId, minPrice, maxPrice, pageable);
        return books.map(bookMapper::toDto);
    }
    
    // Report-related methods
    
    public int getTotalBookCount() {
        Integer count = bookRepository.countTotalBooks();
        return count != null ? count : 0;
    }
    
    public double getTotalInventoryValue() {
        BigDecimal value = bookRepository.calculateTotalInventoryValue();
        return value != null ? value.doubleValue() : 0.0;
    }
    
    public Map<String, Integer> getBookCountByCategory() {
        List<Object[]> results = bookRepository.countBooksByGenre();
        Map<String, Integer> booksByCategory = new HashMap<>();
        
        for (Object[] result : results) {
            String category = (String) result[0];
            if (category == null) category = "Uncategorized";
            Long count = (Long) result[1];
            booksByCategory.put(category, count.intValue());
        }
        
        return booksByCategory;
    }
    
    public List<BookDto> getLowStockBooks(int threshold) {
        List<Book> books = bookRepository.findBooksWithLowStock(threshold);
        return books.stream().map(bookMapper::toDto).collect(Collectors.toList());
    }
    
    public Map<String, Double> getInventoryValueByCategory() {
        List<Object[]> results = bookRepository.calculateInventoryValueByGenre();
        Map<String, Double> valueByCategory = new HashMap<>();
        
        for (Object[] result : results) {
            String category = (String) result[0];
            if (category == null) category = "Uncategorized";
            BigDecimal value = (BigDecimal) result[1];
            valueByCategory.put(category, value != null ? value.doubleValue() : 0.0);
        }
        
        return valueByCategory;
    }
}
