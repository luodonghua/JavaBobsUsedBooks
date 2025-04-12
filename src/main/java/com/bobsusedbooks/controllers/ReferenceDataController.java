package com.bobsusedbooks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bobsusedbooks.dtos.BookTypeDto;
import com.bobsusedbooks.dtos.ConditionDto;
import com.bobsusedbooks.dtos.GenreDto;
import com.bobsusedbooks.dtos.PublisherDto;
import com.bobsusedbooks.services.ReferenceDataService;

@Controller
@RequestMapping("/admin/reference-data")
public class ReferenceDataController {

    @Autowired
    private ReferenceDataService referenceDataService;
    
    // Genre operations
    @PostMapping("/genres/{id}/delete")
    public String deleteGenre(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            referenceDataService.deleteGenre(id);
            redirectAttributes.addFlashAttribute("success", "Genre deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete genre: " + e.getMessage());
        }
        return "redirect:/admin/reference-data";
    }
    
    @PostMapping("/genres/add")
    public String addGenre(@RequestParam String name, @RequestParam String description, RedirectAttributes redirectAttributes) {
        try {
            GenreDto genreDto = new GenreDto();
            genreDto.setName(name);
            genreDto.setDescription(description);
            referenceDataService.createGenre(genreDto);
            redirectAttributes.addFlashAttribute("success", "Genre added successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to add genre: " + e.getMessage());
        }
        return "redirect:/admin/reference-data";
    }
    
    @PostMapping("/genres/{id}/edit")
    public String editGenre(@PathVariable Long id, @RequestParam String name, @RequestParam String description, RedirectAttributes redirectAttributes) {
        try {
            GenreDto genreDto = new GenreDto();
            genreDto.setId(id);
            genreDto.setName(name);
            genreDto.setDescription(description);
            referenceDataService.updateGenre(genreDto);
            redirectAttributes.addFlashAttribute("success", "Genre updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update genre: " + e.getMessage());
        }
        return "redirect:/admin/reference-data";
    }
    
    // Publisher operations
    @PostMapping("/publishers/{id}/delete")
    public String deletePublisher(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            referenceDataService.deletePublisher(id);
            redirectAttributes.addFlashAttribute("success", "Publisher deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete publisher: " + e.getMessage());
        }
        return "redirect:/admin/reference-data";
    }
    
    @PostMapping("/publishers/add")
    public String addPublisher(@RequestParam String name, @RequestParam String description, RedirectAttributes redirectAttributes) {
        try {
            PublisherDto publisherDto = new PublisherDto();
            publisherDto.setName(name);
            publisherDto.setDescription(description);
            referenceDataService.createPublisher(publisherDto);
            redirectAttributes.addFlashAttribute("success", "Publisher added successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to add publisher: " + e.getMessage());
        }
        return "redirect:/admin/reference-data";
    }
    
    @PostMapping("/publishers/{id}/edit")
    public String editPublisher(@PathVariable Long id, @RequestParam String name, @RequestParam String description, RedirectAttributes redirectAttributes) {
        try {
            PublisherDto publisherDto = new PublisherDto();
            publisherDto.setId(id);
            publisherDto.setName(name);
            publisherDto.setDescription(description);
            referenceDataService.updatePublisher(publisherDto);
            redirectAttributes.addFlashAttribute("success", "Publisher updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update publisher: " + e.getMessage());
        }
        return "redirect:/admin/reference-data";
    }
    
    // Book Type operations
    @PostMapping("/book-types/{id}/delete")
    public String deleteBookType(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            referenceDataService.deleteBookType(id);
            redirectAttributes.addFlashAttribute("success", "Book type deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete book type: " + e.getMessage());
        }
        return "redirect:/admin/reference-data";
    }
    
    @PostMapping("/book-types/add")
    public String addBookType(@RequestParam String name, @RequestParam String description, RedirectAttributes redirectAttributes) {
        try {
            BookTypeDto bookTypeDto = new BookTypeDto();
            bookTypeDto.setName(name);
            bookTypeDto.setDescription(description);
            referenceDataService.createBookType(bookTypeDto);
            redirectAttributes.addFlashAttribute("success", "Book type added successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to add book type: " + e.getMessage());
        }
        return "redirect:/admin/reference-data";
    }
    
    @PostMapping("/book-types/{id}/edit")
    public String editBookType(@PathVariable Long id, @RequestParam String name, @RequestParam String description, RedirectAttributes redirectAttributes) {
        try {
            BookTypeDto bookTypeDto = new BookTypeDto();
            bookTypeDto.setId(id);
            bookTypeDto.setName(name);
            bookTypeDto.setDescription(description);
            referenceDataService.updateBookType(bookTypeDto);
            redirectAttributes.addFlashAttribute("success", "Book type updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update book type: " + e.getMessage());
        }
        return "redirect:/admin/reference-data";
    }
    
    // Condition operations
    @PostMapping("/conditions/{id}/delete")
    public String deleteCondition(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            referenceDataService.deleteCondition(id);
            redirectAttributes.addFlashAttribute("success", "Condition deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete condition: " + e.getMessage());
        }
        return "redirect:/admin/reference-data";
    }
    
    @PostMapping("/conditions/add")
    public String addCondition(@RequestParam String name, @RequestParam String description, RedirectAttributes redirectAttributes) {
        try {
            ConditionDto conditionDto = new ConditionDto();
            conditionDto.setName(name);
            conditionDto.setDescription(description);
            referenceDataService.createCondition(conditionDto);
            redirectAttributes.addFlashAttribute("success", "Condition added successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to add condition: " + e.getMessage());
        }
        return "redirect:/admin/reference-data";
    }
    
    @PostMapping("/conditions/{id}/edit")
    public String editCondition(@PathVariable Long id, @RequestParam String name, @RequestParam String description, RedirectAttributes redirectAttributes) {
        try {
            ConditionDto conditionDto = new ConditionDto();
            conditionDto.setId(id);
            conditionDto.setName(name);
            conditionDto.setDescription(description);
            referenceDataService.updateCondition(conditionDto);
            redirectAttributes.addFlashAttribute("success", "Condition updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update condition: " + e.getMessage());
        }
        return "redirect:/admin/reference-data";
    }
}
