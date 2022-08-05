package ru.otus.spring.spring08.test.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import ru.otus.spring.spring08.domain.Author;
import ru.otus.spring.spring08.domain.Book;
import ru.otus.spring.spring08.domain.Comment;
import ru.otus.spring.spring08.domain.Genre;
import ru.otus.spring.spring08.repository.AuthorRepository;
import ru.otus.spring.spring08.repository.BookRepository;
import ru.otus.spring.spring08.repository.CommentRepository;
import ru.otus.spring.spring08.repository.GenreRepository;

import java.util.Arrays;

@ChangeLog
public class DatabaseChangeLogTest {

    @ChangeSet(order = "001", id = "dropDb", author = "alex", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertGenre", author = "alex")
    public void insertGenre(GenreRepository repository) {
        Genre fantastic=new Genre("fantastic");
        fantastic.setId(1);
        repository.save(fantastic);
        Genre detective=new Genre("detective");
        detective.setId(2);
        repository.save(detective);
    }

    @ChangeSet(order = "003", id = "insertAuthor", author = "alex")
    public void insertAuthor(AuthorRepository repository) {
        Author aCristy =new Author("Agatha Christie");
        aCristy.setId(1);
        repository.save(aCristy);
    }


    @ChangeSet(order = "004", id = "insertBookComment", author = "alex")
    public void insertBookComment(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, CommentRepository commentRepository){
        Genre genre=genreRepository.findById(1).get();
        Author author=authorRepository.findById(1).get();
        Comment comment1=new Comment("comment1");
        comment1.setId(1);
        commentRepository.save(comment1);
        Comment comment2=new Comment("comment2");
        comment2.setId(2);
        commentRepository.save(comment2);

        Book book=new Book("The Mysterious Affair at Styles",genre,author, Arrays.asList(comment1,comment2));
        book.setId(1);
        bookRepository.save(book);
    }


    @ChangeSet(order = "005", id = "insertSeq", author = "alex")
    public void insertSeq(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("database_sequences");
        Document doc = new Document().append("_id", Book.SEQUENCE_NAME).append("seq",3);
        myCollection.insertOne(doc);
        Document doc2 = new Document().append("_id", Comment.SEQUENCE_NAME).append("seq",3);
        myCollection.insertOne(doc2);
    }



}
