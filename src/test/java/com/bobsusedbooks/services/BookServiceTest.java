package com.bobsusedbooks.services;

import com.bobsusedbooks.dtos.BookDto;
import com.bobsusedbooks.entities.Book;
import com.bobsusedbooks.mappers.BookMapper;
import com.bobsusedbooks.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    
    @Mock
    private BookRepository bookRepository;
    
    @Mock
    private BookMapper bookMapper;
    
    @InjectMocks
    private BookService bookService;
    
    private Book book1;
    private Book book2;
    private BookDto bookDto1;
    private BookDto bookDto2;
    
    @BeforeEach
    void setUp() {
        // Setup test data
        book1 = new Book();
        book1.setId(1L);
        book1.setName("Test Book 1");
        book1.setAuthor("Test Author 1");
        book1.setIsbn("1234567890");
        book1.setGenreId(1L);
        book1.setPublisherId(1L);
        book1.setBookTypeId(1L);
        book1.setConditionId(1L);
        book1.setPrice(new BigDecimal("19.99"));
        book1.setQuantity(10);
        
        book2 = new Book();
        book2.setId(2L);
        book2.setName("Test Book 2");
        book2.setAuthor("Test Author 2");
        book2.setIsbn("0987654321");
        book2.setGenreId(1L);
        book2.setPublisherId(1L);
        book2.setBookTypeId(1L);
        book2.setConditionId(1L);
        book2.setPrice(new BigDecimal("29.99"));
        book2.setQuantity(5);
        
        bookDto1 = new BookDto();
        bookDto1.setId(1L);
        bookDto1.setName("Test Book 1");
        bookDto1.setAuthor("Test Author 1");
        bookDto1.setIsbn("1234567890");
        bookDto1.setGenreId(1L);
        bookDto1.setPublisherId(1L);
        bookDto1.setBookTypeId(1L);
        bookDto1.setConditionId(1L);
        bookDto1.setPrice(new BigDecimal("19.99"));
        bookDto1.setQuantity(10);
        
        bookDto2 = new BookDto();
        bookDto2.setId(2L);
        bookDto2.setName("Test Book 2");
        bookDto2.setAuthor("Test Author 2");
        bookDto2.setIsbn("0987654321");
        bookDto2.setGenreId(1L);
        bookDto2.setPublisherId(1L);
        bookDto2.setBookTypeId(1L);
        bookDto2.setConditionId(1L);
        bookDto2.setPrice(new BigDecimal("29.99"));
        bookDto2.setQuantity(5);
    }
    
    @Test
    void shouldGetAllBooks() {
        // Given
        List<Book> books = Arrays.asList(book1, book2);
        when(bookRepository.findAll()).thenReturn(books);
        when(bookMapper.toDto(book1)).thenReturn(bookDto1);
        when(bookMapper.toDto(book2)).thenReturn(bookDto2);
        
        // When
        List<BookDto> result = bookService.getAllBooks();
        
        // Then
        assertThat(result).hasSize(2);
        assertThat(result).contains(bookDto1, bookDto2);
    }
    
    @Test
    void shouldGetAllBooksPaginated() {
        // Given
        List<Book> books = Arrays.asList(book1, book2);
        Page<Book> bookPage = new PageImpl<>(books);
        Pageable pageable = PageRequest.of(0, 10);
        
        when(bookRepository.findAll(pageable)).thenReturn(bookPage);
        when(bookMapper.toDto(book1)).thenReturn(bookDto1);
        when(bookMapper.toDto(book2)).thenReturn(bookDto2);
        
        // When
        Page<BookDto> result = bookService.getAllBooksPaginated(pageable);
        
        // Then
        assertThat(result).hasSize(2);
        assertThat(result.getContent()).contains(bookDto1, bookDto2);
    }
    
    @Test
    void shouldGetBookById() {
        // Given
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));
        when(bookMapper.toDto(book1)).thenReturn(bookDto1);
        
        // When
        Optional<BookDto> result = bookService.getBookById(1L);
        
        // Then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookDto1);
    }
    
    @Test
    void shouldSearchBooks() {
        // Given
        String searchString = "Test";
        List<Book> books = Arrays.asList(book1);
        
        when(bookRepository.findByNameContainingIgnoreCaseOrAuthorContainingIgnoreCase(searchString, searchString))
            .thenReturn(books);
        when(bookMapper.toDto(book1)).thenReturn(bookDto1);
        
        // When
        List<BookDto> result = bookService.searchBooks(searchString);
        
        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(bookDto1);
    }
    
    @Test
    void shouldGetBooksByGenre() {
        // Given
        Long genreId = 1L;
        List<Book> books = Arrays.asList(book1, book2);
        
        when(bookRepository.findByGenreId(genreId)).thenReturn(books);
        when(bookMapper.toDto(book1)).thenReturn(bookDto1);
        when(bookMapper.toDto(book2)).thenReturn(bookDto2);
        
        // When
        List<BookDto> result = bookService.getBooksByGenre(genreId);
        
        // Then
        assertThat(result).hasSize(2);
        assertThat(result).contains(bookDto1, bookDto2);
    }
    
    @Test
    void shouldCreateBook() {
        // Given
        when(bookMapper.toEntity(any(BookDto.class))).thenReturn(book1);
        when(bookRepository.save(any(Book.class))).thenReturn(book1);
        when(bookMapper.toDto(book1)).thenReturn(bookDto1);
        
        // When
        BookDto result = bookService.createBook(bookDto1);
        
        // Then
        assertThat(result).isEqualTo(bookDto1);
    }
    
    @Test
    void shouldCreateBookWithSeller() {
        // Given
        when(bookMapper.toEntity(any(BookDto.class))).thenReturn(book1);
        when(bookRepository.save(any(Book.class))).thenReturn(book1);
        when(bookMapper.toDto(book1)).thenReturn(bookDto1);
        
        // When
        BookDto result = bookService.createBook(bookDto1);
        
        // Then
        assertThat(result).isEqualTo(bookDto1);
    }
}
