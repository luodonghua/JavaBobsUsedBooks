package com.bobsusedbooks.repositories;

import com.bobsusedbooks.entities.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void shouldFindByNameContainingIgnoreCaseOrAuthorContainingIgnoreCase() {
        // Given
        Book book1 = new Book("Test Book", "Test Author", new BigDecimal("19.99"));
        Book book2 = new Book("Another Book", "Another Author", new BigDecimal("29.99"));
        bookRepository.saveAll(List.of(book1, book2));

        // When
        List<Book> result = bookRepository.findByNameContainingIgnoreCaseOrAuthorContainingIgnoreCase("test", "test");

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Test Book");
    }

    @Test
    void shouldFindByGenreId() {
        // Given
        Book book1 = new Book("Test Book", "Test Author", new BigDecimal("19.99"));
        book1.setGenreId(1L);
        Book book2 = new Book("Another Book", "Another Author", new BigDecimal("29.99"));
        book2.setGenreId(2L);
        bookRepository.saveAll(List.of(book1, book2));

        // When
        List<Book> result = bookRepository.findByGenreId(1L);

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Test Book");
    }

    @Test
    void shouldFindByIsFeaturedTrue() {
        // Given
        Book book1 = new Book("Test Book", "Test Author", new BigDecimal("19.99"));
        book1.setIsFeatured(true);
        Book book2 = new Book("Another Book", "Another Author", new BigDecimal("29.99"));
        book2.setIsFeatured(false);
        bookRepository.saveAll(List.of(book1, book2));

        // When
        List<Book> result = bookRepository.findByIsFeaturedTrue();

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Test Book");
    }

    @Test
    void shouldFindByAdvancedSearch() {
        // Given
        Book book1 = new Book("Test Book", "Test Author", new BigDecimal("19.99"));
        book1.setGenreId(1L);
        book1.setPublisherId(1L);
        book1.setBookTypeId(1L);
        book1.setConditionId(1L);
        
        Book book2 = new Book("Another Book", "Another Author", new BigDecimal("29.99"));
        book2.setGenreId(2L);
        book2.setPublisherId(2L);
        book2.setBookTypeId(2L);
        book2.setConditionId(2L);
        
        bookRepository.saveAll(List.of(book1, book2));
        
        Pageable pageable = PageRequest.of(0, 10);

        // When
        Page<Book> result = bookRepository.findByAdvancedSearch(
                "Test", null, null, 1L, 1L, 1L, null, 
                new BigDecimal("10.00"), new BigDecimal("20.00"), pageable);

        // Then
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getName()).isEqualTo("Test Book");
    }
}
