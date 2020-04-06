package com.polinashlepakova.books.controller;

import com.polinashlepakova.books.dto.BookDTO;
import com.polinashlepakova.books.dto.RegistrationDTO;
import com.polinashlepakova.books.entity.BookEntity;
import com.polinashlepakova.books.service.IUserService;
import com.polinashlepakova.books.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody final RegistrationDTO user) {
        if (userService.loginExists(user.getLogin())) {
            return new ResponseEntity<>("Login already exists",
                    HttpStatus.FORBIDDEN);
        }
        String userPassword = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(userPassword);
        user.setPassword(encodedPassword);
        userService.registerAsUser(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/books/favorites")
    public ResponseEntity<List<BookDTO>> getFavorites(final Principal principal) {
        String login = principal.getName();
        return ok(Converter.toBookDTOList(userService.findFavorites(login)));
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/books/favorites/add")
    public ResponseEntity addToFavorites(
            final Principal principal, @RequestBody final BookEntity book) {
        String login = principal.getName();
        userService.addToFavorites(book, login);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/books/favorites/delete")
    public ResponseEntity deleteFromFavorites(
            final Principal principal, @RequestBody final BookEntity book) {
        String login = principal.getName();
        userService.deleteFromFavorites(book, login);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/books/{id}/is-favorited")
    public ResponseEntity<Boolean> isFavorited(
            final Principal principal, @PathVariable("id") final int bookId) {
        if (principal == null) {
            return ok(false);
        }
        String login = principal.getName();
        return ok(userService.isFavorited(bookId, login));
    }

}
