package ru.otus.spring.spring12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.spring12.domain.Book;
import ru.otus.spring.spring12.domain.Comment;
import ru.otus.spring.spring12.dto.BookDto;
import ru.otus.spring.spring12.dto.CommentDto;
import ru.otus.spring.spring12.service.*;

@Controller
public class CommentController {
    private BookService bookService;
    private CommentService commentService;

    public CommentController(BookService bookService, CommentServiceImpl commentService){
        this.bookService = bookService;
        this.commentService = commentService;
    }

    @GetMapping("/commentList")
    public String commentList(@RequestParam("bookId") int bookId, Model model){
        Book book=bookService.findById(bookId);
        model.addAttribute("bookId",bookId);
        model.addAttribute("book", new BookDto(book));
        return "comment/commentList";
    }

    @GetMapping("/addComment")
    public String addCommentPage(@RequestParam("bookId") int bookId, Model model){
        model.addAttribute("bookId",bookId);
        model.addAttribute("comment", new CommentDto());
        return "comment/addComment";
    }

    @PostMapping("/addComment")
    public  String addComment(@RequestParam("bookId") int bookId, CommentDto commentDto){
        commentDto.setBookId(bookId);
        commentService.add(commentDto);
        return "redirect:/commentList?bookId="+String.valueOf(bookId);
    }

    @GetMapping("/editComment")
    public String editCommentPage(@RequestParam("bookId") int bookId,@RequestParam("commentId") int commentId, Model model){
        model.addAttribute("bookId",bookId);
        model.addAttribute("commentId",commentId);
        Comment comment= commentService.findById(commentId);
        model.addAttribute("comment", new CommentDto(comment));
        return "comment/editComment";
    }

    @PostMapping("/editComment")
    public String editComment(@RequestParam("bookId") int bookId, CommentDto commentDto){
        commentService.update(commentDto);
        return "redirect:/commentList?bookId="+String.valueOf(bookId);
    }

    @GetMapping("/confirmDeleteComment")
    public String confirmDeleteComment(@RequestParam("bookId") int bookId,@RequestParam("commentId") int commentId, Model model){
        model.addAttribute("bookId",bookId);
        model.addAttribute("commentId",commentId);
        Comment comment= commentService.findById(commentId);
        model.addAttribute("comment", new CommentDto(comment));
        return "comment/confirmDeleteComment";
    }

    @PostMapping("/deleteComment")
    public String deleteComment(@RequestParam("bookId") int bookId, CommentDto commentDto){
        commentService.delete(commentDto.getId());
        return "redirect:/commentList?bookId="+String.valueOf(bookId);
    }


}
