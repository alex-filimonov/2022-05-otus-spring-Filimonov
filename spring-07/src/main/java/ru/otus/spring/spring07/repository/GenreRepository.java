package ru.otus.spring.spring07.repository;

<<<<<<< HEAD
import ru.otus.spring.spring07.models.Book;
import ru.otus.spring.spring07.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Genre save(Genre genre);
    Optional<Genre> findById(int id);
    List<Genre> findAll();
    void updateNameById(int id, String name);
    void deleteById(int id);
=======
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.spring07.domain.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {
>>>>>>> master
}
