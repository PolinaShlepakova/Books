package com.polinashlepakova.books.service;

import com.polinashlepakova.books.dto.RegistrationDTO;
import com.polinashlepakova.books.entity.BookEntity;
import com.polinashlepakova.books.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    UserEntity save(final UserEntity user);

    Optional<UserEntity> findByLogin(final String login);

    boolean loginExists(final String login);

    UserEntity registerAsUser(final RegistrationDTO user);

    void addToFavorites(final BookEntity book, final String userLogin);

    void deleteFromFavorites(final BookEntity book, final String userLogin);

    List<BookEntity> findFavorites(final int userId);

    List<BookEntity> findFavorites(final String login);

    boolean isFavorited(final int bookId, final String login);
}
