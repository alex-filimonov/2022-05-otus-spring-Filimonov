package ru.otus.spring.spring06.repository;

import org.springframework.stereotype.Repository;
import ru.otus.spring.spring06.models.Book;
import ru.otus.spring.spring06.models.Comment;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpa implements CommentRepository{
    @PersistenceContext
    private EntityManager em;


    public CommentRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public Optional<Comment> findById(int id) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.id= :id", Comment.class);
        query.setParameter("id", id);
        return Optional.of(query.getResultList().get(0));
    }

    @Override
    public List<Comment> findByBookId(int bookId) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.book_id=:bookId", Comment.class);
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }

    @Override
    public void updateDataById(int id, String data) {
        Query query = em.createQuery("update Comment c " +
                "set c.data = :name " +
                "where b.id = :id");
        query.setParameter("name", data);
        query.setParameter("id", id);
        query.executeUpdate();

    }

    @Override
    public void deleteById(int id) {
        Query query = em.createQuery("delete " +
                "from Comment s " +
                "where s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
