package ru.otus.spring.spring11.rest.dto;

import lombok.Data;
import ru.otus.spring.spring11.domain.Comment;

@Data
public class CommentDto {
    private int id;
    private String data;
    public CommentDto(int id, String data){
        this.id=id;
        this.data=data;
    }
}
