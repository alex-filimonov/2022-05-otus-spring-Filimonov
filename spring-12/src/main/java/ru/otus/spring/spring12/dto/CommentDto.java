package ru.otus.spring.spring12.dto;

import lombok.Data;
import ru.otus.spring.spring12.domain.Comment;

@Data
public class CommentDto {
    private int id;

    private int bookId;
    private String data;

    public CommentDto(){};
    public CommentDto(Comment comment){
        this.id=comment.getId();
        this.data=comment.getData();
        this.bookId= comment.getBook().getId();
    }
}
