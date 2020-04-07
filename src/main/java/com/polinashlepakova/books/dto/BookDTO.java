package com.polinashlepakova.books.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private Integer id;

    @NotEmpty(message = "Title can't be empty")
    String title;

    @NotEmpty(message = "Author can't be empty")
    String author;

    @Pattern(regexp = "^(?:ISBN(?:-1[03])?:? )?" +
            "(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|" +
            "97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)" +
            "(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$",
            message = "ISBN must have correct format")
    String isbn;
}