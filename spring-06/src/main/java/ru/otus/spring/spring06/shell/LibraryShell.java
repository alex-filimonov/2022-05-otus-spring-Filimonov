package ru.otus.spring.spring06.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;
import ru.otus.spring.spring06.models.Author;
import ru.otus.spring.spring06.models.Book;
import ru.otus.spring.spring06.models.Comment;
import ru.otus.spring.spring06.models.Genre;
import ru.otus.spring.spring06.repository.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ShellComponent
@Transactional
public class LibraryShell {
    BookRepository bookRepository;
    GenreRepository genreRepository;
    AuthorRepository authorRepository;
    CommentRepository commentRepository;

    private String MESSAGE_OK="ok";


    LibraryShell(BookRepository bookRepository,GenreRepository genreRepository,AuthorRepository authorRepository, CommentRepository commentRepository){
        this.bookRepository=bookRepository;
        this.authorRepository=authorRepository;
        this.genreRepository=genreRepository;
        this.commentRepository=commentRepository;
    }

    @ShellMethod("book-getAll")
    @Transactional
    public String bookGetAll(){
        List<Book> bookList=bookRepository.findAll();
        List<List<String>> data=new ArrayList<>();
        data.add(Arrays.asList(new String[]{"ID","BOOK_NAME","AUTHOR","GENRE"}));
        bookList.forEach(book -> {
            data.add(Arrays.asList(new String(String.valueOf(book.getId())),book.getName().toString(),book.getAuthor().getName(),book.getGenre().getName()));
        });
        return getString(data);
    }

    @ShellMethod("book-get")
    @Transactional
    public String bookGet(int id){
        Book book=bookRepository.findById(id).get();
        List<List<String>> data=new ArrayList<>();
        data.add(Arrays.asList(new String[]{"ID","BOOK_NAME","AUTHOR","GENRE"}));
        data.add(Arrays.asList(new String(String.valueOf(book.getId())),book.getName(),book.getAuthor().getName(),book.getGenre().getName()));
        return getString(data);
    }

    @ShellMethod("book-add")
    @Transactional
    public String bookAdd(String bookName,int authorId, int genreId){
        Author author=authorRepository.findById(authorId).get();
        Genre genre=genreRepository.findById(genreId).get();
        Book book=new Book();
        book.setName(bookName);
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepository.save(book);
        return MESSAGE_OK;
    }

    @ShellMethod("book-update")
    @Transactional
    public String bookUpdate(int bookId, String bookName,int authorId, int genreId){
        Author author=authorRepository.findById(authorId).get();
        Genre genre=genreRepository.findById(genreId).get();
        Book book=bookRepository.findById(bookId).get();
        book.setName(bookName);
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepository.save(book);
        return MESSAGE_OK;
    }

    @ShellMethod("book-delete")
    @Transactional
    public String bookDelete(int bookId){
        bookRepository.deleteById(bookId);
        return MESSAGE_OK;
    }

    @ShellMethod("comments-get")
    public String commentsGet(int bookId){
        Book book=bookRepository.findById(bookId).get();
        List<List<String>> data=new ArrayList<>();
        data.add(Arrays.asList(new String[]{"ID","BOOK_ID","BOOK_NAME","COMMENT_DATA"}));
        book.getCommentList().forEach(comment->{
            data.add(Arrays.asList(new String(String.valueOf(comment.getId())),String.valueOf(comment.getBook_id()),book.getName(),comment.getData()));
        });
        return getString(data);
    }

    @ShellMethod("comment-add")
    public String commentAdd(int bookId, String data){
        Comment comment=new Comment();
        comment.setBook_id(bookId);
        comment.setData(data);
        commentRepository.save(comment);
        return MESSAGE_OK;
    }

    @ShellMethod("comment-update")
    public String commentUpdate(int id, int bookId, String data){
        Comment comment=commentRepository.findById(id).get();
        comment.setBook_id(bookId);
        comment.setData(data);
        commentRepository.save(comment);
        return MESSAGE_OK;
    }

    @ShellMethod("comment-delete")
    public String commentDelete(int id){
        commentRepository.deleteById(id);
        return MESSAGE_OK;
    }



    private String getString(List<List<String>> data) {
        TableModel model= (TableModel) new ArrayTableModel(data.stream().map(x->x.toArray(new Object[x.size()])).toArray(Object[][]::new));
        TableBuilder tableBuilder = new TableBuilder( model);
        tableBuilder.addFullBorder(BorderStyle.fancy_light);
        return tableBuilder.build().render(80);
    }

}
