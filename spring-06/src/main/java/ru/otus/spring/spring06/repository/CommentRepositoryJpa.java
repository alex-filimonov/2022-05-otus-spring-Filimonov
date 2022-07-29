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
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public void updateDataById(int id, String data) {
        this.findById(id).ifPresent(c->{
            c.setData(data);
            this.save(c);
        });
    }

    @Override
    public void deleteById(int id) {
        this.findById(id).ifPresent(c -> {em.remove(c);});
    }
}
