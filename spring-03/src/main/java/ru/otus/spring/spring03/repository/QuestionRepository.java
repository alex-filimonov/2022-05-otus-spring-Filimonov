package ru.otus.spring.spring03.repository;

import ru.otus.spring.spring03.model.Answer;
import ru.otus.spring.spring03.model.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionRepository {
    List<Question> getQuestionByStringList(List<String> lines);

    Answer getAnswerFromQuestionAndNumberAnswer(Question question, int number);
}
