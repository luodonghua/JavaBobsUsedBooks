package com.bobsusedbooks.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {
    
    @GetMapping("/marketplace")
    public String marketplace() {
        return "redirect:/sell";
    }
    
    @GetMapping("/sell-books")
    public String sellBooks() {
        return "redirect:/sell";
    }
    
    @GetMapping("/privacy")
    public String privacy() {
        return "privacy";
    }

    
}
