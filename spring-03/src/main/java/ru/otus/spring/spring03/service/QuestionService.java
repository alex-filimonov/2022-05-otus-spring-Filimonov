package ru.otus.spring.spring03.service;

import ru.otus.spring.spring03.model.Answer;
import ru.otus.spring.spring03.model.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions(List<String> lines);

    Answer getAnswerFromQuestionAndNumberAnswer(Question question, int number);
}
