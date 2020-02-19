package com.polinashlepakova.books.repository;

import com.polinashlepakova.books.entity.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookRepository {

    private final List<Book> books;

    public BookRepository() {
        this.books = new ArrayList<>();
//        books.add(new Book("book1", "auth1", "4327894285"));
//        books.add(new Book("book2", "auth1", "3476877989"));
//        books.add(new Book("book3", "auth2", "9423472385"));
    }

    public List<Book> findAll() {
        return books;
    }

    public void save(final Book book) {
        books.add(book);
    }
}
