package com.bobsusedbooks.controllers;

import com.bobsusedbooks.dtos.BookDto;
import com.bobsusedbooks.services.BookService;
import com.bobsusedbooks.services.ReferenceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;
    
    @Autowired
    private ReferenceDataService referenceDataService;

    // HTML view endpoints
    @GetMapping
    public String getAllBooks(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) Long publisherId,
            @RequestParam(required = false) Long bookTypeId,
            @RequestParam(required = false) Long genreId,
            @RequestParam(required = false) Long conditionId,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String sort,
            Model model) {
        
        // Parse sort parameter
        String[] sortParams = sort.split(",");
        String sortField = sortParams[0];
        Sort.Direction direction = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc") ? 
            Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        
        // Use advanced search with all filters
        Page<BookDto> books = bookService.findBooks(
            keyword, author, isbn, publisherId, bookTypeId, genreId, conditionId, minPrice, maxPrice, pageable);
        
        // Add reference data for filters
        model.addAttribute("books", books);
        model.addAttribute("currentPage", page);
        model.addAttribute("publishers", referenceDataService.getAllPublishers());
        model.addAttribute("bookTypes", referenceDataService.getAllBookTypes());
        model.addAttribute("genres", referenceDataService.getAllGenres());
        model.addAttribute("conditions", referenceDataService.getAllConditions());
        
        return "books/index";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable Long id, Model model) {
        Optional<BookDto> book = bookService.getBookById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "books/details";
        } else {
            return "redirect:/books";
        }
    }

    @GetMapping("/genre/{genreId}")
    public String getBooksByGenre(@PathVariable Long genreId, Model model) {
        // Create a pageable for initial display
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name"));
        
        // Get books by genre using the standard repository method
        List<BookDto> genreBooks = bookService.getBooksByGenre(genreId);
        
        // Convert to Page object for consistency with the template
        Page<BookDto> books = new PageImpl<>(genreBooks, pageable, genreBooks.size());
        
        // Add reference data for filters
        model.addAttribute("books", books);
        model.addAttribute("currentPage", 0);
        model.addAttribute("publishers", referenceDataService.getAllPublishers());
        model.addAttribute("bookTypes", referenceDataService.getAllBookTypes());
        model.addAttribute("genres", referenceDataService.getAllGenres());
        model.addAttribute("conditions", referenceDataService.getAllConditions());
        model.addAttribute("genreId", genreId);
        
        return "books/index";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam String query, Model model) {
        // Create a pageable for initial display
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name"));
        
        // Use the simple search method
        Page<BookDto> books = bookService.searchBooksPaginated(query, pageable);
        
        // Add reference data for filters
        model.addAttribute("books", books);
        model.addAttribute("currentPage", 0);
        model.addAttribute("publishers", referenceDataService.getAllPublishers());
        model.addAttribute("bookTypes", referenceDataService.getAllBookTypes());
        model.addAttribute("genres", referenceDataService.getAllGenres());
        model.addAttribute("conditions", referenceDataService.getAllConditions());
        model.addAttribute("keyword", query);
        
        return "books/index";
    }

    @GetMapping("/featured")
    public String getFeaturedBooks(Model model) {
        model.addAttribute("featuredBooks", bookService.getFeaturedBooks());
        return "home/index";
    }
    
    // Admin endpoints for book management
    @GetMapping("/admin")
    public String adminBooks(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long genreId,
            @RequestParam(required = false) Long publisherId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String sort,
            Model model) {
        
        // Parse sort parameter
        String[] sortParams = sort.split(",");
        String sortField = sortParams[0];
        Sort.Direction direction = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc") ? 
            Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        
        // Get books with filters
        Page<BookDto> books;
        if (keyword != null && !keyword.trim().isEmpty()) {
            books = bookService.searchBooksPaginated(keyword, pageable);
        } else if (genreId != null) {
            List<BookDto> genreBooks = bookService.getBooksByGenre(genreId);
            books = new PageImpl<>(genreBooks, pageable, genreBooks.size());
        } else if (publisherId != null) {
            // Assuming you have a method to get books by publisher
            books = bookService.getAllBooksPaginated(pageable); // Replace with actual method
        } else {
            books = bookService.getAllBooksPaginated(pageable);
        }
        
        model.addAttribute("books", books);
        model.addAttribute("genres", referenceDataService.getAllGenres());
        model.addAttribute("publishers", referenceDataService.getAllPublishers());
        return "admin/books/index";
    }
    
    @GetMapping("/admin/create")
    public String createBookForm(Model model) {
        model.addAttribute("book", new BookDto());
        model.addAttribute("publishers", referenceDataService.getAllPublishers());
        model.addAttribute("bookTypes", referenceDataService.getAllBookTypes());
        model.addAttribute("genres", referenceDataService.getAllGenres());
        model.addAttribute("conditions", referenceDataService.getAllConditions());
        return "admin/books/create";
    }
    
    @PostMapping("/admin/create")
    public String createBook(@ModelAttribute BookDto bookDto) {
        bookService.createBook(bookDto);
        return "redirect:/books/admin";
    }
    
    @GetMapping("/admin/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        Optional<BookDto> book = bookService.getBookById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            model.addAttribute("publishers", referenceDataService.getAllPublishers());
            model.addAttribute("bookTypes", referenceDataService.getAllBookTypes());
            model.addAttribute("genres", referenceDataService.getAllGenres());
            model.addAttribute("conditions", referenceDataService.getAllConditions());
            return "admin/books/edit";
        } else {
            return "redirect:/books/admin";
        }
    }
    
    @PostMapping("/admin/edit/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute BookDto bookDto) {
        bookDto.setId(id);
        bookService.updateBook(bookDto);
        return "redirect:/books/admin";
    }
    
    @PostMapping("/admin/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books/admin";
    }
    
    // API endpoints for programmatic access
    @GetMapping(value = "", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<BookDto>> getAllBooksApi() {
        List<BookDto> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    
    @GetMapping(value = "/paginated", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Page<BookDto>> getAllBooksPaginatedApi(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<BookDto> books = bookService.getAllBooksPaginated(pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    
    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<BookDto> getBookByIdApi(@PathVariable Long id) {
        Optional<BookDto> book = bookService.getBookById(id);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @GetMapping(value = "/genre/{genreId}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<BookDto>> getBooksByGenreApi(@PathVariable Long genreId) {
        List<BookDto> books = bookService.getBooksByGenre(genreId);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    
    @GetMapping(value = "/search", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<BookDto>> searchBooksApi(@RequestParam String query) {
        List<BookDto> books = bookService.searchBooks(query);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    
    @PostMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<BookDto> createBookApi(@RequestBody BookDto bookDto) {
        BookDto createdBook = bookService.createBook(bookDto);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<BookDto> updateBookApi(@PathVariable Long id, @RequestBody BookDto bookDto) {
        bookDto.setId(id);
        BookDto updatedBook = bookService.updateBook(bookDto);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Void> deleteBookApi(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping(value = "/filter", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Page<BookDto>> filterBooksApi(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) Long publisherId,
            @RequestParam(required = false) Long bookTypeId,
            @RequestParam(required = false) Long genreId,
            @RequestParam(required = false) Long conditionId,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? 
            Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        
        // Use the advanced search method
        Page<BookDto> books = bookService.findBooks(
            keyword, author, isbn, publisherId, bookTypeId, genreId, conditionId, minPrice, maxPrice, pageable);
        
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
