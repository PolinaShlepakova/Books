package com.polinashlepakova.books.service;

import com.polinashlepakova.books.entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface IBookService {

    BookEntity save(final BookEntity book);

    List<BookEntity> findAll();

    Optional<BookEntity> findById(final int id);

    List<BookEntity> findByTitleOrIsbn(final String text);
}
