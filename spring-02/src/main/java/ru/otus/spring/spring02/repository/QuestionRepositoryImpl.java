package ru.otus.spring.spring02.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.otus.spring.spring02.dao.QuestionDao;
import ru.otus.spring.spring02.dto.AnswerDto;
import ru.otus.spring.spring02.model.Answer;
import ru.otus.spring.spring02.model.Question;
import ru.otus.spring.spring02.service.CSVResourceService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

    private QuestionDao questionDao;
    private AnswerRepository answerRepository;
    private static final String CSV_DELIMITER = ";";

    public QuestionRepositoryImpl(QuestionDao questionDao, AnswerRepository answerRepository){
        this.questionDao = questionDao;
        this.answerRepository = answerRepository;
    }

    public List<Question> getQuestionByStringList(List<String> lines){
        List<Question> questionList = new ArrayList<>();
        lines.forEach(line ->{
            String[] fields = line.split(CSV_DELIMITER);
            if (fields.length > 1) {
                Question question = questionDao.create(Integer.parseInt(fields[0]), fields[1]);
                question.setAnswerList(answerRepository.getAnswersFromArray(Arrays.copyOfRange(fields, 2, fields.length)));
                questionList.add(question);
            }
        });
        return questionList;
    }

    public Answer getAnswerFromQuestionAndNumberAnswer(Question question,int number){
        return answerRepository.getAnswerByNumber(question.getAnswerList(),number);
    }

}
