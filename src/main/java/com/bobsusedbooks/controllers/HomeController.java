package com.bobsusedbooks.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to Bob's Used Books!");
        return "home/index";
    }
    
    
    @GetMapping("/health")
    @ResponseBody
    public String health() {
        return "Application is running!";
    }
}
