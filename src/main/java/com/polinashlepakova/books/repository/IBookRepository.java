package com.polinashlepakova.books.repository;

import com.polinashlepakova.books.entity.BookEntity;
import com.polinashlepakova.books.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBookRepository extends JpaRepository<BookEntity, Integer> {

    List<BookEntity> findAllByTitleContainingIgnoreCaseOrIsbnContaining(
            final String title, final String isbn);

    @Query("SELECT book FROM BookEntity book "
            + "JOIN book.users user "
            + "WHERE user.id = :user_id")
    List<BookEntity> findFavoritesForUser(@Param("user_id") final int userId);

    @Query("SELECT book FROM BookEntity book "
            + "JOIN book.users user "
            + "WHERE user.login = :login")
    List<BookEntity> findFavoritesForUser(@Param("login") final String login);

    boolean existsAllByIdAndUsersContains(final int bookId, final UserEntity user);
}
