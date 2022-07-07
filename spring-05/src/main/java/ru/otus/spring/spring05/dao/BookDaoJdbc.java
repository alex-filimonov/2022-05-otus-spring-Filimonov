package ru.otus.spring.spring05.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.spring05.domain.Author;
import ru.otus.spring.spring05.domain.Book;
import ru.otus.spring.spring05.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class BookDaoJdbc implements BookDao{

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        Integer count = namedParameterJdbcOperations.getJdbcOperations().queryForObject("select count(*) from books", Integer.class);
        return count == null? 0: count;
    }

    @Override
    public Book add(Book book) {
        Map<String, Object> param= Stream.of(new Object[][] {
                { "name", book.getName() },
                { "author_id", book.getAuthor().getId() },
                { "genre_id", book.getGenre().getId() }
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Object) data[1]));
        int newId=namedParameterJdbcOperations.update("insert into books ( name, author_id, genre_id) values (:name, :author_id, :genre_id)",param);
        book.setId(newId);
        return book;
    }

    @Override
    public void update(Book book){
        Map<String, Object> param= Stream.of(new Object[][] {
                { "id", book.getId() },
                { "name", book.getName() },
                { "author_id", book.getAuthor().getId() },
                { "genre_id", book.getGenre().getId() }
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Object) data[1]));
        namedParameterJdbcOperations.update("update books set name=:name, author_id=:author_id, genre_id=:genre_id where id=:id",param);
    }

    @Override
    public void delete(Book book){
        Map<String, Object> param= Stream.of(new Object[][] {
                { "id", book.getId() }
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Object) data[1]));
        namedParameterJdbcOperations.update("delete from books where id=:id",param);
    }

    @Override
    public void deleteById(long id){
        Map<String, Object> param= Stream.of(new Object[][] {
                { "id", id }
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Object) data[1]));
        namedParameterJdbcOperations.update("delete from books where id=:id",param);
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select b.id as b_id, b.name as b_name, a.id as a_id, a.name as a_name, g.id as g_id, g.name as g_name " +
                        "from books b " +
                        "left join authors a on (b.author_id=a.id) " +
                        "left join genres g on (b.genre_id=g.id)  where b.id = :id", params, new BookDaoJdbc.BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query(
                "select b.id as b_id, b.name as b_name, a.id as a_id, a.name as a_name, g.id as g_id, g.name as g_name " +
                        "from books b " +
                        "left join authors a on (b.author_id=a.id) " +
                        "left join genres g on (b.genre_id=g.id)",  new BookDaoJdbc.BookMapper()
        );
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Book(
                    resultSet.getInt("b_id"),
                    resultSet.getString("b_name"),
                    new Author(
                            resultSet.getInt("a_id"),
                            resultSet.getString("a_name")
                    ),
                    new Genre(
                            resultSet.getInt("g_id"),
                            resultSet.getString("g_name")
                    )
            );
        }
    }

}
