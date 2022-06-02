package ru.otus.spring.spring02.service;

import ru.otus.spring.spring02.model.Answer;
import ru.otus.spring.spring02.model.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions(List<String> lines);

    Answer getAnswerFromQuestionAndNumberAnswer(Question question, int number);
}
