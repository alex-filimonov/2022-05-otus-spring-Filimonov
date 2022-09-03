package ru.otus.spring.spring11.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookDto {
    private String id;
    private String name;
    private AuthorDto author;
    private GenreDto genre;

    private List<CommentDto> comments;


    public BookDto(String id, String name, AuthorDto author, GenreDto genre, List<CommentDto> comments){
        this.id=id;
        this.name=name;
        this.author=author;
        this.genre=genre;
        this.comments=comments;

    }

}
