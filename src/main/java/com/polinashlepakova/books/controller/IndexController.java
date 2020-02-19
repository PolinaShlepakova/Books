package com.polinashlepakova.books.controller;

import com.polinashlepakova.books.entity.Book;
import com.polinashlepakova.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class IndexController {

    private final BookRepository bookRepository;

    @Autowired
    public IndexController(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping({ "/", "" })
    public String index(final Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "index";
    }

    @RequestMapping(value = "/add-book", method = RequestMethod.POST)
    public String addBook(@ModelAttribute final Book book) {
        bookRepository.save(book);
        return "redirect:/";
    }



}
