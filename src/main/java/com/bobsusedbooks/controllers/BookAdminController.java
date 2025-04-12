package com.bobsusedbooks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bobsusedbooks.services.BookService;

/**
 * This controller is intentionally left empty as the functionality
 * is already implemented in BookController. This class exists only
 * to resolve the ambiguous mapping error.
 */
@Controller
@RequestMapping("/books/admin-extra")
public class BookAdminController {

    @Autowired
    private BookService bookService;
    
    // This method is not used, but kept to maintain the class structure
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            bookService.deleteBook(id);
            redirectAttributes.addFlashAttribute("success", "Book deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete book: " + e.getMessage());
        }
        return "redirect:/books/admin";
    }
}
