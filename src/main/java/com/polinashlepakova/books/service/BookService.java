package com.polinashlepakova.books.service;

import com.polinashlepakova.books.entity.BookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final EntityManager entityManager;

    @Transactional
    public BookEntity save(final BookEntity book) {
        return entityManager.merge(book);
    }

    @Transactional
    public List<BookEntity> findAll() {
        return entityManager.createNamedQuery(
                BookEntity.FIND_ALL, BookEntity.class)
                .getResultList();
    }

    @Transactional
    public BookEntity findById(int id) {
        return entityManager.find(BookEntity.class, id);
    }

    @Transactional
    public List<BookEntity> findByTitle(final String title) {
        return entityManager.createNamedQuery(
                BookEntity.FIND_BY_TITLE, BookEntity.class)
                .setParameter("title", title)
                .getResultList();
    }
}
