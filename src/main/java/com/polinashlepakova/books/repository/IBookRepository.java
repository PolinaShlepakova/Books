package com.polinashlepakova.books.repository;

import com.polinashlepakova.books.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBookRepository extends JpaRepository<BookEntity, Integer> {

    List<BookEntity> findAllByTitleContainingIgnoreCaseOrIsbnContaining(
            final String title, final String isbn);
}
