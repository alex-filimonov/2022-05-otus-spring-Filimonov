package ru.otus.spring.spring11.rest.dto;

import lombok.Data;

@Data
public class BookDto {
    private String id;
    private String name;
    private AuthorDto author;
    private GenreDto genre;

    public BookDto(String id, String name, AuthorDto author, GenreDto genre){
        this.id=id;
        this.name=name;
        this.author=author;
        this.genre=genre;
    }

}
