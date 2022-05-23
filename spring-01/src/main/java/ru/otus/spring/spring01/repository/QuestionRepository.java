package ru.otus.spring.spring01.repository;

import ru.otus.spring.spring01.model.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionRepository {
    List<Question> getAll() throws IOException;
}
