package ru.otus.spring.spring02.service;

import ru.otus.spring.spring02.model.Answer;
import ru.otus.spring.spring02.model.Question;

import java.util.List;

public interface QuestionService {
    void outputQuestions();
    List<Question> getAllQuestions();
    Answer getAnswerFromQuestionAndNumberAnswer(Question question, int number);
}
