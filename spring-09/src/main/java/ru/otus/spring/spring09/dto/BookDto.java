package ru.otus.spring.spring09.dto;


import lombok.Data;

import java.util.List;

@Data
public class BookDto {
    private int id;
    private String name;
    private AuthorDto author;
    private GenreDto genre;
    private List<CommentDto> commentList;
}
