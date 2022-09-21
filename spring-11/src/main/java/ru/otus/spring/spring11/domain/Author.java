package ru.otus.spring.spring11.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="author")
@Data
public class Author {
    @Transient
    public static final String SEQUENCE_NAME = "authors_sequence";

    @Id
    private int id;
    private String name;

    public Author(int id,String name){
        this.id=id;
        this.name=name;
    }

}
