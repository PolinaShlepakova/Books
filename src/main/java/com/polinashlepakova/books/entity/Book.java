package com.polinashlepakova.books.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    String title;
    String author;
    String isbn;
}
