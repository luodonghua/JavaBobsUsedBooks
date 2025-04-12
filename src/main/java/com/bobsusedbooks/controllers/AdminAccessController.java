package com.bobsusedbooks.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin-access")
public class AdminAccessController {

    @GetMapping
    public String redirectToAdminLogin(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Please login with admin credentials to access the admin portal");
        return "redirect:/admin/login";
    }
}
