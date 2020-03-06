package com.polinashlepakova.books.controller;

import com.polinashlepakova.books.entity.BookEntity;
import com.polinashlepakova.books.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @RequestMapping({ "/", "" })
    public String index(final Model model) {
        List<BookEntity> books = bookService.findAll();
        model.addAttribute("books", books);
        return "index";
    }

    @RequestMapping(value = "/book/{id}")
    public String findById(final Model model, @PathVariable final int id) {
        BookEntity book = bookService.findById(id);
        model.addAttribute("book", book);
        return "book";
    }

    @RequestMapping(value = "/add-book", method = RequestMethod.POST)
    public String addBook(@ModelAttribute final BookEntity book) {
        bookService.save(book);
        return "redirect:/";
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String findByTitle(final Model model,
                              @RequestParam final String title) {
        List<BookEntity> books = bookService.findByTitle(title);
        model.addAttribute("books", books);
        return "books";
    }

}
