package ru.otus.spring.spring13.dto;


import lombok.Data;
import ru.otus.spring.spring13.domain.Book;

@Data
public class BookDto {
    private int id;
    private String name;
    private AuthorDto author;
    private GenreDto genre;

    public BookDto(){
        this.author=new AuthorDto();
        this.genre=new GenreDto();
    }

    public BookDto(Book book){
        this.id=book.getId();
        this.name=book.getName();
        this.author=new AuthorDto(book.getAuthor());
        this.genre=new GenreDto(book.getGenre());
    }
}
