package ru.otus.spring.spring10.dto;

import lombok.Data;
import ru.otus.spring.spring10.domain.Comment;

@Data
public class CommentDto {
    private int id;

    private Long bookId;
    private String data;

    public CommentDto(){};
    public CommentDto(Comment comment){
        this.id=comment.getId();
        this.data=comment.getData();
        this.bookId= comment.getBook().getId();
    }
}
