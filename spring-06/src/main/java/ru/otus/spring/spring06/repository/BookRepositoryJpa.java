package ru.otus.spring.spring06.repository;

import org.springframework.stereotype.Repository;
import ru.otus.spring.spring06.models.Book;
import ru.otus.spring.spring06.shell.LibraryShell;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryJpa implements BookRepository{

    @PersistenceContext
    private EntityManager em;


    public BookRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Book save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Optional<Book> findById(int id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-genre-author-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public void updateNameById(int id, String name) {
        this.findById(id).ifPresent(b->{
            b.setName(name);
            this.save(b);
        });
    }

    @Override
    public void deleteById(int id) {
        this.findById(id).ifPresent(c -> {em.remove(c);});
    }
}
