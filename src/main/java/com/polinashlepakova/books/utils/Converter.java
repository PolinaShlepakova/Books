package com.polinashlepakova.books.utils;

import com.polinashlepakova.books.dto.BookDTO;
import com.polinashlepakova.books.entity.BookEntity;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public static BookDTO toBookDTO(final BookEntity book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .build();
    }

    public static List<BookDTO> toBookDTOList(final List<BookEntity> books) {
        List<BookDTO> res = new ArrayList<>();
        for (BookEntity book : books) {
            res.add(toBookDTO(book));
        }
        return res;
    }
}
