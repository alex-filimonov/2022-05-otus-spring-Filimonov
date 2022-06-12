package ru.otus.spring.spring02.repository;

import ru.otus.spring.spring02.model.Answer;
import ru.otus.spring.spring02.model.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionRepository {
    List<Question> getQuestionByStringList(List<String> lines);

    Answer getAnswerFromQuestionAndNumberAnswer(Question question, int number);
}
