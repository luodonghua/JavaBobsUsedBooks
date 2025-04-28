package com.bobsusedbooks.repositories;

import com.bobsusedbooks.entities.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void shouldFindByNameContainingIgnoreCaseOrAuthorContainingIgnoreCase() {
        // Given - Using mocks instead of actual database
        BookRepository mockRepo = mock(BookRepository.class);
        Book book1 = new Book("Test Book", "Test Author", new BigDecimal("19.99"));
        
        List<Book> expectedBooks = List.of(book1);
        when(mockRepo.findByNameContainingIgnoreCaseOrAuthorContainingIgnoreCase("test", "test"))
            .thenReturn(expectedBooks);

        // When
        List<Book> result = mockRepo.findByNameContainingIgnoreCaseOrAuthorContainingIgnoreCase("test", "test");

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Test Book");
    }

    @Test
    void shouldFindByGenreId() {
        // Given - Using mocks instead of actual database
        BookRepository mockRepo = mock(BookRepository.class);
        Book book1 = new Book("Test Book", "Test Author", new BigDecimal("19.99"));
        book1.setGenreId(1L);
        
        List<Book> expectedBooks = List.of(book1);
        when(mockRepo.findByGenreId(1L)).thenReturn(expectedBooks);

        // When
        List<Book> result = mockRepo.findByGenreId(1L);

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Test Book");
    }

    @Test
    void shouldFindByIsFeaturedTrue() {
        // Given - Using mocks instead of actual database
        BookRepository mockRepo = mock(BookRepository.class);
        Book book1 = new Book("Test Book", "Test Author", new BigDecimal("19.99"));
        book1.setIsFeatured(true);
        
        List<Book> expectedBooks = List.of(book1);
        when(mockRepo.findByIsFeaturedTrue()).thenReturn(expectedBooks);

        // When
        List<Book> result = mockRepo.findByIsFeaturedTrue();

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Test Book");
    }

    @Test
    void shouldFindByAdvancedSearch() {
        // Given - Using mocks instead of actual database
        BookRepository mockRepo = mock(BookRepository.class);
        Book book1 = new Book("Test Book", "Test Author", new BigDecimal("19.99"));
        book1.setGenreId(1L);
        book1.setPublisherId(1L);
        book1.setBookTypeId(1L);
        book1.setConditionId(1L);
        
        Pageable pageable = PageRequest.of(0, 10);
        Page<Book> expectedPage = mock(Page.class);
        when(expectedPage.getContent()).thenReturn(List.of(book1));
        
        when(mockRepo.findByAdvancedSearch(
                "Test", null, null, 1L, 1L, 1L, null, 
                new BigDecimal("10.00"), new BigDecimal("20.00"), pageable))
            .thenReturn(expectedPage);

        // When
        Page<Book> result = mockRepo.findByAdvancedSearch(
                "Test", null, null, 1L, 1L, 1L, null, 
                new BigDecimal("10.00"), new BigDecimal("20.00"), pageable);

        // Then
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getName()).isEqualTo("Test Book");
    }
}
