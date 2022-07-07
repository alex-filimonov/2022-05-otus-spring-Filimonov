package ru.otus.spring.spring04.repository;

import ru.otus.spring.spring04.model.Answer;
import ru.otus.spring.spring04.model.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionRepository {
    List<Question> getQuestionByStringList(List<String> lines);

    Answer getAnswerFromQuestionAndNumberAnswer(Question question, int number);
}
