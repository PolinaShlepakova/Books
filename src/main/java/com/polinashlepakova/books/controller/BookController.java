package com.polinashlepakova.books.controller;

import com.polinashlepakova.books.dto.BookDTO;
import com.polinashlepakova.books.entity.BookEntity;
import com.polinashlepakova.books.service.IBookService;
import com.polinashlepakova.books.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> getBooks(
            @RequestParam(name = "text", required = false) final String text) {
        List<BookEntity> books;
        if (text == null) {
            books = bookService.findAll();
        } else {
            books = bookService.findByTitleOrIsbn(text);
        }
        return ResponseEntity.ok(Converter.toBookDTOList(books));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable final int id) {
        Optional<BookEntity> book = bookService.findById(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(Converter.toBookDTO(book.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/books")
    public ResponseEntity<BookDTO> addBook(@RequestBody final BookDTO book) {
        BookEntity bookEntity = bookService.save(BookEntity.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .build());
        return new ResponseEntity<>(
                Converter.toBookDTO(bookEntity), HttpStatus.CREATED);
    }

}
