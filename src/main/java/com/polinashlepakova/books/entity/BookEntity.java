package com.polinashlepakova.books.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
@NamedQueries({
        @NamedQuery(query = "SELECT b FROM BookEntity b",
                name = BookEntity.FIND_ALL),
        @NamedQuery(query = "SELECT b FROM BookEntity b WHERE " +
                "LOWER(b.title) LIKE CONCAT('%', LOWER(:title), '%')",
                name = BookEntity.FIND_BY_TITLE)
})
public class BookEntity {

    public static final String FIND_ALL = "BookEntity.FIND_ALL";
    public static final String FIND_BY_TITLE = "BookEntity.FIND_BY_TITLE";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "title")
    String title;

    @Column(name = "author")
    String author;

    @Column(name = "isbn")
    String isbn;
}
