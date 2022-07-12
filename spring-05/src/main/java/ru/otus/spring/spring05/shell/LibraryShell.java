package ru.otus.spring.spring05.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;
import ru.otus.spring.spring05.dao.BookDao;
import ru.otus.spring.spring05.domain.Author;
import ru.otus.spring.spring05.domain.Book;
import ru.otus.spring.spring05.domain.Genre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@ShellComponent
public class LibraryShell {
    private String MESSAGE_OK="ok";
    private BookDao bookDao;

    LibraryShell(BookDao bookDao){
        this.bookDao=bookDao;
    }

    @ShellMethod("book-getAll")
    public String bookGetAll(){
        List<Book> bookList=bookDao.getAll();
        List<List<String>> data=new ArrayList<>();
        data.add(Arrays.asList(new String[]{"ID","BOOK_NAME","AUTHOR","GENRE"}));
        bookList.forEach(book -> {
            data.add(Arrays.asList(new String(String.valueOf(book.getId())),book.getName().toString(),book.getAuthor().getName(),book.getGenre().getName()));
        });
        return getString(data);
    }

    @ShellMethod("book-get")
    public String bookGet(int id){
        Book book=bookDao.getById(id);
        List<List<String>> data=new ArrayList<>();
        data.add(Arrays.asList(new String[]{"ID","BOOK_NAME","AUTHOR","GENRE"}));
        data.add(Arrays.asList(new String(String.valueOf(book.getId())),book.getName().toString(),book.getAuthor().getName(),book.getGenre().getName()));
        return getString(data);
    }

    private String getString(List<List<String>> data) {
        TableModel model= (TableModel) new ArrayTableModel(data.stream().map(x->x.toArray(new Object[x.size()])).toArray(Object[][]::new));
        TableBuilder tableBuilder = new TableBuilder( model);
        tableBuilder.addFullBorder(BorderStyle.fancy_light);
        return tableBuilder.build().render(80);
    }

    @ShellMethod("book-add")
    public String bookAdd(String bookName,int authorId, int genreId){
        bookDao.add(new Book(0,bookName,new Author(authorId,""),new Genre(genreId,"")));
        return MESSAGE_OK;
    }

    @ShellMethod("book-update")
    public String bookUpdate(int bookId, String bookName,int authorId, int genreId){
        Book book=bookDao.getById(bookId);
        book.setName(bookName);
        book.getAuthor().setId(authorId);
        book.getGenre().setId(genreId);
        bookDao.update(book);
        return MESSAGE_OK;
    }

    @ShellMethod("book-count")
    public String bookCount(){
        int count=bookDao.count();
        return String.valueOf(count);
    }

    @ShellMethod("book-delete")
    public String bookDelete(int bookId){
        bookDao.deleteById(bookId);
        return MESSAGE_OK;
    }

}
