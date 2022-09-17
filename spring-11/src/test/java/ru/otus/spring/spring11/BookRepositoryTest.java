package ru.otus.spring.spring11;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.spring.spring11.domain.Author;
import ru.otus.spring.spring11.domain.Book;
import ru.otus.spring.spring11.domain.Genre;
import ru.otus.spring.spring11.repository.BookRepository;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataMongoTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;


    @Test
    public void getBooks(){
        Mono<Book> bookMono = bookRepository.save(new Book("test",new Genre(0,"detective"),new Author(0,"Haynlayn"), Collections.emptyList()));
        StepVerifier.create(bookMono)
                .assertNext(book->assertNotNull(book.getId()))
                .expectComplete()
                .verify();

        Flux<Book> books=bookRepository.findAll();
        books.subscribe(
                book->{
                    assertNotNull(book.getId());
                }
        );

    }

    @Test
    public void addBook(){
        Mono<Book> bookMono = bookRepository.save(new Book("test",new Genre(0,"detective"),new Author(0,"Haynlayn"), Collections.emptyList()));
        StepVerifier.create(bookMono)
                .assertNext(book->assertNotNull(book.getId()))
                .expectComplete()
                .verify();
    }

    @Test
    public void getBook(){
        Mono<Book> bookMono = bookRepository.save(new Book("test",new Genre(0,"detective"),new Author(0,"Haynlayn"), Collections.emptyList()));
        AtomicReference<String> id=new AtomicReference<>();
        StepVerifier.create(bookMono)
                .assertNext(book->{
                    assertNotNull(book.getId());
                    id.set(book.getId());
                })
                .expectComplete()
                .verify();
        System.out.println(id.get());
        Mono<Book> bookDtoMono=bookRepository.findById(id.get());
        StepVerifier.create(bookDtoMono)
                .assertNext(b->{
                            assertEquals(b.getName(),"test");
                        })
                .expectComplete()
                .verify();
    }

    @Test
    public void updateBook(){
        Mono<Book> bookMono = bookRepository.save(new Book("test",new Genre(0,"detective"),new Author(0,"Haynlayn"), Collections.emptyList()));
        AtomicReference<String> id=new AtomicReference<>();
        StepVerifier.create(bookMono)
                .assertNext(book->{
                    assertNotNull(book.getId());
                    id.set(book.getId());
                })
                .expectComplete()
                .verify();

        Mono<Book> bookDtoMono=bookRepository.findById(id.get());
        AtomicReference<Book> updateBook=new AtomicReference<>();
        StepVerifier.create(bookDtoMono)
                .assertNext(b->{
                    assertEquals(b.getName(),"test");
                    b.setName("test_update");
                    updateBook.set(b);
                })
                .expectComplete()
                .verify();

        Mono<Book> updateBookMono=bookRepository.save(updateBook.get());
        StepVerifier.create(updateBookMono)
                .assertNext(b->{
                    assertEquals(b.getName(),"test_update");
                })
                .expectComplete()
                .verify();
    }









}
