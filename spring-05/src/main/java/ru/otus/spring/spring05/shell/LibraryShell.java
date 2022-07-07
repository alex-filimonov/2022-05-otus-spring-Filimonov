package ru.otus.spring.spring05.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.BeanListTableModel;
import org.springframework.shell.table.Table;
import org.springframework.shell.table.TableBuilder;
import ru.otus.spring.spring05.dao.BookDao;
import ru.otus.spring.spring05.dao.BookDaoJdbc;
import ru.otus.spring.spring05.domain.Book;

import javax.swing.table.TableModel;
import java.util.LinkedHashMap;
import java.util.List;

@ShellComponent
public class LibraryShell {
    private BookDao bookDao;

    LibraryShell(BookDao bookDao){
        this.bookDao=bookDao;
    }

    @ShellMethod("book-getAll")
    public String bookGetAll(){
        List<Book> bookList=bookDao.getAll();
        LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
        headers.put("name", "Name");
        //final TableBuilder builder = new TableBuilder(new BeanListTableModel<>(bookList,headers));
        //return DataFlowTables.applyStyle(builder).build();
        return bookList.toString();
    }

}
