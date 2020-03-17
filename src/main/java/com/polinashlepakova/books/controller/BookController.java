package com.polinashlepakova.books.controller;

import com.polinashlepakova.books.entity.BookEntity;
import com.polinashlepakova.books.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;

    @RequestMapping({ "/", "" })
    public String index(final Model model) {
        List<BookEntity> books = bookService.findAll();
        model.addAttribute("books", books);
        return "index";
    }

    @RequestMapping(value = "/books/{id}")
    public String findById(final Model model, @PathVariable final int id) {
        Optional<BookEntity> book = bookService.findById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "book";
        } else {
            model.addAttribute("message", "Book not found.");
            return "error";
        }
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public String addBook(@ModelAttribute final BookEntity book) {
        bookService.save(book);
        return "redirect:/";
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String findByTitle(final Model model,
                              @RequestParam final String text) {
        List<BookEntity> books = bookService.findByTitleOrIsbn(text);
        model.addAttribute("books", books);
        return "books";
    }

}
