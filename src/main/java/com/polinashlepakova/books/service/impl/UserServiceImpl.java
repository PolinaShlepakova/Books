package com.polinashlepakova.books.service.impl;

import com.polinashlepakova.books.dto.RegistrationDTO;
import com.polinashlepakova.books.entity.BookEntity;
import com.polinashlepakova.books.entity.Permission;
import com.polinashlepakova.books.entity.PermissionEntity;
import com.polinashlepakova.books.entity.UserEntity;
import com.polinashlepakova.books.repository.IBookRepository;
import com.polinashlepakova.books.repository.IUserRepository;
import com.polinashlepakova.books.service.IUserService;
import com.polinashlepakova.books.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IBookRepository bookRepository;

    @Override
    public UserEntity save(final UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<UserEntity> findByLogin(final String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public boolean loginExists(final String login) {
        return userRepository.existsAllByLogin(login);
    }

    @Override
    public UserEntity registerAsUser(final RegistrationDTO user) {
        PermissionEntity userPermission =
                new PermissionEntity(Constants.USER_PERMISSION_ID,
                        Permission.USER);

        return save(UserEntity.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .permissions(Collections.singletonList(userPermission))
                .build());
    }

    @Override
    public void addToFavorites(final BookEntity book, final String userLogin) {
        Optional<UserEntity> optionalUser = findByLogin(userLogin);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            List<BookEntity> favorites = findFavorites(user.getId());
            favorites.add(book);
            user.setFavorites(favorites);
            save(user);
        }
    }

    @Override
    public void deleteFromFavorites(final BookEntity book,
                                    final String userLogin) {
        Optional<UserEntity> optionalUser = findByLogin(userLogin);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            List<BookEntity> favorites = findFavorites(user.getId());
            boolean res = favorites.remove(book);
            user.setFavorites(favorites);
            save(user);
        }
    }

    @Override
    public List<BookEntity> findFavorites(final int userId) {
        return bookRepository.findFavoritesForUser(userId);
    }

    @Override
    public List<BookEntity> findFavorites(String login) {
        return bookRepository.findFavoritesForUser(login);
    }

    @Override
    public boolean isFavorited(int bookId, String login) {
        Optional<UserEntity> user = findByLogin(login);
        return user.filter(u -> bookRepository.existsAllByIdAndUsersContains(bookId, u)).isPresent();
    }
}
