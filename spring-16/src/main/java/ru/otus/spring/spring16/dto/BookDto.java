package ru.otus.spring.spring16.dto;


import lombok.Data;
import ru.otus.spring.spring16.domain.Book;
import ru.otus.spring.spring16.domain.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BookDto {
    private int id;
    private String name;
    private AuthorDto author;
    private GenreDto genre;
    private List<CommentDto> commentList;

    public BookDto(){
        this.author=new AuthorDto();
        this.genre=new GenreDto();
    }

    public BookDto(Book book){
        this.id=book.getId();
        this.name=book.getName();
        this.author=new AuthorDto(book.getAuthor());
        this.genre=new GenreDto(book.getGenre());
        this.commentList=book.getCommentList().stream().map(c->new CommentDto(c)).collect(Collectors.toList());
    }
}
