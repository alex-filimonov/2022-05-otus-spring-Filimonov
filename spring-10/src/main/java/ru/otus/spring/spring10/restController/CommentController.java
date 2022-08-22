package ru.otus.spring.spring10.restController;

import org.springframework.web.bind.annotation.*;
import ru.otus.spring.spring10.dto.CommentDto;
import ru.otus.spring.spring10.service.BookService;
import ru.otus.spring.spring10.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommentController {
    private CommentService commentService;
    private BookService bookService;

    public CommentController(CommentService commentService, BookService bookService){
        this.commentService=commentService;
        this.bookService=bookService;
    }

    @GetMapping("/api/comments/{id}")
    public List<CommentDto> getAll(@PathVariable Long id){
        return bookService.findById(id).getCommentList().stream().map(c->(new CommentDto(c))).collect(Collectors.toList());
    }

    @GetMapping("/api/comment/{id}")
    public CommentDto get(@PathVariable Long id){
        return new CommentDto(commentService.findById(id));
    }

    @PostMapping("/api/comment/{id}")
    public void add(@PathVariable Long id, @RequestBody CommentDto commentDto){
        commentDto.setBookId(id);
        commentService.add(commentDto);
    }

    @PutMapping("/api/comment/{bookId}/{id}")
    public void update(@PathVariable Long bookId, @PathVariable Long id, @RequestBody CommentDto commentDto){
        commentDto.setBookId(bookId);
        commentService.update(commentDto);
    }

    @DeleteMapping("/api/comment/{id}")
    public void delete(@PathVariable Long id){
        commentService.delete(id);
    }

}
