package com.polinashlepakova.books.service.impl;

import com.polinashlepakova.books.entity.BookEntity;
import com.polinashlepakova.books.repository.IBookRepository;
import com.polinashlepakova.books.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {

    private final IBookRepository bookRepository;

    @Override
    public BookEntity save(final BookEntity book) {
        return bookRepository.save(book);
    }

    @Override
    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<BookEntity> findById(final int id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<BookEntity> findByTitleOrIsbn(final String text) {
        return bookRepository.
                findAllByTitleContainingIgnoreCaseOrIsbnContaining(text, text);
    }

}
