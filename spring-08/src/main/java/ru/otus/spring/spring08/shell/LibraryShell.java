package ru.otus.spring.spring08.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;
import ru.otus.spring.spring08.domain.Book;
import ru.otus.spring.spring08.service.BookService;
import ru.otus.spring.spring08.service.CommentService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ShellComponent
public class LibraryShell {
    private BookService bookService;
    private CommentService commentService;

    private String MESSAGE_OK="ok";


    LibraryShell(BookService bookService, CommentService commentService){
        this.bookService=bookService;
        this.commentService=commentService;
    }

    @ShellMethod("book-getAll")
    public String bookGetAll(){
        List<Book> bookList=bookService.getAll();
        List<List<String>> data=new ArrayList<>();
        data.add(Arrays.asList(new String[]{"ID","BOOK_NAME","AUTHOR","GENRE"}));
        bookList.forEach(book -> {
            data.add(Arrays.asList(new String(String.valueOf(book.getId())),book.getName().toString(),book.getAuthor().getName(),book.getGenre().getName()));
        });
        return getString(data);
    }

    @ShellMethod("book-get")
    public String bookGet(int id){
        Book book=bookService.findById(id);
        List<List<String>> data=new ArrayList<>();
        data.add(Arrays.asList(new String[]{"ID","BOOK_NAME","AUTHOR","GENRE"}));
        data.add(Arrays.asList(new String(String.valueOf(book.getId())),book.getName(),book.getAuthor().getName(),book.getGenre().getName()));
        return getString(data);
    }
    @ShellMethod("book-add")
    public String bookAdd(String bookName,int authorId, int genreId){
        bookService.add(bookName,authorId,genreId);
        return MESSAGE_OK;
    }
/*
    @ShellMethod("book-update")
    @Transactional
    public String bookUpdate(int bookId, String bookName,int authorId, int genreId){
        bookService.update(bookId, bookName, authorId, genreId);
        return MESSAGE_OK;
    }

    @ShellMethod("book-delete")
    @Transactional
    public String bookDelete(int bookId){
        bookService.delete(bookId);
        return MESSAGE_OK;
    }

    @ShellMethod("comments-get")
    public String commentsGet(int bookId){
        Book book=bookService.findById(bookId);
        List<List<String>> data=new ArrayList<>();
        data.add(Arrays.asList(new String[]{"ID","BOOK_NAME","COMMENT_DATA"}));
        book.getCommentList().forEach(comment->{
            data.add(Arrays.asList(new String(String.valueOf(comment.getId())),book.getName(),comment.getData()));
        });
        return getString(data);
    }

    @ShellMethod("comment-add")
    public String commentAdd(int bookId, String data){
        commentService.add(bookId, data);
        return MESSAGE_OK;
    }
    @ShellMethod("comment-update")
    public String commentUpdate(int id, int bookId, String data){
        commentService.update(id, bookId, data);
        return MESSAGE_OK;
    }

    @ShellMethod("comment-delete")
    public String commentDelete(int id){
        commentService.delete(id);
        return MESSAGE_OK;
    }
*/
    private String getString(List<List<String>> data) {
        TableModel model= (TableModel) new ArrayTableModel(data.stream().map(x->x.toArray(new Object[x.size()])).toArray(Object[][]::new));
        TableBuilder tableBuilder = new TableBuilder( model);
        tableBuilder.addFullBorder(BorderStyle.fancy_light);
        return tableBuilder.build().render(80);
    }
}
