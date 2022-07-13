package ru.otus.spring.spring05.domain;

import lombok.*;

@Data
@AllArgsConstructor
public class Book {
    private int id;
    private String name;
    private Author author;
    private Genre genre;
}
