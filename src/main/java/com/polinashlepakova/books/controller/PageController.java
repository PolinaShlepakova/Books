package com.polinashlepakova.books.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @GetMapping("/register-page")
    public String getRegisterPage() {
        return "register";
    }

    @GetMapping("/book-page/{id}")
    public String getBookPage(final Model model, @PathVariable final int id) {
        model.addAttribute("bookId", id);
        return "book";
    }
}
