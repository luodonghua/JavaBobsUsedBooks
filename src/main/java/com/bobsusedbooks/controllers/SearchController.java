package com.bobsusedbooks.controllers;

import com.bobsusedbooks.dtos.BookDto;
import com.bobsusedbooks.entities.Book;
import com.bobsusedbooks.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    private final BookService bookService;
    @Autowired
    public SearchController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search")
    public String search(
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "name") String sort,
            @RequestParam(defaultValue = "asc") String direction,
            Model model) {
        
        if (query != null && !query.trim().isEmpty()) {
            // Clean the query by removing extra spaces
            String cleanQuery = query.trim().replaceAll("\\s+", " ");
            
            // Create pageable with sorting
            Sort.Direction sortDirection = direction.equalsIgnoreCase("asc") ? 
                Sort.Direction.ASC : Sort.Direction.DESC;
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sort));
            
            // Get paginated search results
            Page<BookDto> bookPage = bookService.searchBooksPaginated(cleanQuery, pageable);
            
            model.addAttribute("books", bookPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", bookPage.getTotalPages());
            model.addAttribute("totalItems", bookPage.getTotalElements());
            model.addAttribute("query", cleanQuery);
            model.addAttribute("sort", sort);
            model.addAttribute("direction", direction);
            model.addAttribute("size", size);
        }
        
        return "search/index";
    }

    @GetMapping("/fulltextsearch")
    public String fulltextsearch(
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "name") String sort,
            @RequestParam(defaultValue = "asc") String direction,
            Model model) {
        
        if (query != null && !query.trim().isEmpty()) {
            // Clean the query by removing extra spaces
            String cleanQuery = query.trim().replaceAll("\\s+", " ");
            
            // Create pageable with sorting
            Sort.Direction sortDirection = direction.equalsIgnoreCase("asc") ? 
                Sort.Direction.ASC : Sort.Direction.DESC;
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sort));
            
            // Get paginated search results
            Page<BookDto> bookPage = bookService.fullTextSearchBooksPaginated(cleanQuery, pageable);
            
            model.addAttribute("books", bookPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", bookPage.getTotalPages());
            model.addAttribute("totalItems", bookPage.getTotalElements());
            model.addAttribute("query", cleanQuery);
            model.addAttribute("sort", sort);
            model.addAttribute("direction", direction);
            model.addAttribute("size", size);
        }
        
        return "search/index";
    }
}
