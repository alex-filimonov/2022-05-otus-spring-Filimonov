package ru.otus.spring.spring11.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.otus.spring.spring11.repository.GenreRepository;

import java.util.List;


@Document(collection = "book")
@Data
public class Book {

    @Id
    private String id;
    private String name;
    Genre genre;
    Author author;
    List<Comment> commentList;

    public Book(String name, Genre genre, Author author,List<Comment> commentList){
        this.name=name;
        this.genre=genre;
        this.author=author;
        this.commentList=commentList;
    }

}
