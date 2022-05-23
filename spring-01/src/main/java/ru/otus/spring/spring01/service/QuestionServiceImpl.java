package ru.otus.spring.spring01.service;

import ru.otus.spring.spring01.model.Question;
import ru.otus.spring.spring01.repository.QuestionRepository;

import java.io.InputStream;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    private QuestionRepository questionRepository;

    QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void outputQuestions() {
        try {
            List<Question> questions = questionRepository.getAll();
            questions.forEach(question -> {
                System.out.println(question.getName());
                question.getAnswerList().forEach(answer -> {
                    System.out.println("---> " + answer.getName());
                });
            });
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

}
