package ru.otus.spring.spring11.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import ru.otus.spring.spring11.domain.Book;
import ru.otus.spring.spring11.domain.Comment;
import ru.otus.spring.spring11.repository.BookRepository;
import ru.otus.spring.spring11.repository.CommentRepository;
import ru.otus.spring.spring11.rest.dto.CommentDto;

@Service
public class CommentServiceImpl implements CommentService {
    private BookRepository bookRepository;
    private CommentRepository commentRepository;

    public CommentServiceImpl(BookRepository bookRepository,CommentRepository commentRepository){
        this.bookRepository=bookRepository;
        this.commentRepository=commentRepository;
    }

    @Override
    public Flux<CommentDto> getComments(String id){
        Mono<Book> bookMono=bookRepository.findById(id);
        return bookMono.map(b-> b.getCommentList())
                .flatMapMany(Flux::fromIterable)
                .map(c->new CommentDto(c.getId(),c.getData()));
    }

    @Override
    public Mono<CommentDto> getComment(String bookId, String id){
        return commentRepository.findById(id).map(c->new CommentDto(c.getId(),c.getData()));
    }

    @Override
    public Mono<CommentDto> add(String id, CommentDto commentDto){
        Mono<Book> bookMono=bookRepository.findById(id);
        Comment comment=new Comment(commentDto.getData());
        Mono<Comment> commentMono=commentRepository.save(comment);
        Mono<Tuple2<Book,Comment>> tuple2Mono=Mono.zip(bookMono,commentMono);
        return tuple2Mono.flatMap(t->{
            Book b=t.getT1();
            Comment c=t.getT2();
            b.getCommentList().add(c);
            return bookRepository.save(b);
        }).map(book -> book.getCommentList().get(book.getCommentList().size()-1)).map(c->new CommentDto(c.getId(),c.getData()));
    }

    @Override
    public Mono<CommentDto> update(String bookId, String id, CommentDto commentDto){
        Mono<Book> bookMono=bookRepository.findById(bookId);
        Mono<Comment> commentMono=commentRepository.findById(id);
        Mono<Tuple2<Book,Comment>> tuple2Mono=Mono.zip(bookMono,commentMono);
        return tuple2Mono.flatMap(t->{
            Book b=t.getT1();
            Comment c=t.getT2();
            c.setData(commentDto.getData());
            b.getCommentList().stream().filter(it->it.getId().equals(c.getId())).findFirst().get().setData(commentDto.getData());
            commentRepository.save(c);
            return bookRepository.save(b);
        }).map(book -> book.getCommentList().stream().filter(it->it.getId().equals(id)).findFirst().get()).map(c->new CommentDto(c.getId(),c.getData()));
    }

    @Override
    public Mono<Book> delete(String bookId, String id){
        Mono<Book> bookMono=bookRepository.findById(bookId);
        Mono<Comment> commentMono=commentRepository.findById(id);
        Mono<Tuple2<Book,Comment>> tuple2Mono=Mono.zip(bookMono,commentMono);
        return tuple2Mono.flatMap(t->{
            Book b=t.getT1();
            Comment c=t.getT2();
            b.getCommentList().removeIf(i->i.getId().equals(id));
            commentRepository.delete(c);
            return bookRepository.save(b);
        });
    }


}
