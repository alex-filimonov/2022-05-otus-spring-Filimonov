package ru.otus.spring.spring08.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


@Document(collection = "comment")
@Data
public class Comment {
    @Id
    private int id;
    private String data;

    public Comment(String data){
        this.data=data;
    }

}
