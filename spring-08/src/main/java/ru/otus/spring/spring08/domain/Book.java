package ru.otus.spring.spring08.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.otus.spring.spring08.repository.GenreRepository;

import java.util.List;


@Document(collection = "book")
@Data
public class Book {
    @Id
    private int id;
    private String name;
    @DocumentReference
    Genre genre;
    @DocumentReference
    Author author;
    @DocumentReference
    List<Comment> commentList;

    public Book(String name, Genre genre, Author author,List<Comment> commentList){
        this.name=name;
        this.genre=genre;
        this.author=author;
        this.commentList=commentList;
    }

}
