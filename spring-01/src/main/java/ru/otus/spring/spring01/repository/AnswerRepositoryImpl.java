package ru.otus.spring.spring01.repository;

import ru.otus.spring.spring01.dao.AnswerDao;
import ru.otus.spring.spring01.model.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AnswerRepositoryImpl implements AnswerRepository {
    private AnswerDao answerDao;

    public AnswerRepositoryImpl(AnswerDao answerDao) {
        this.answerDao = answerDao;
    }

    public List<Answer> getAnswersFromArray(String[] fields) {
        List<Answer> answers = new ArrayList<>();
        Iterator<String> fieldsIterator = Arrays.stream(fields).iterator();
        while (fieldsIterator.hasNext()) {
            answers.add(
                    answerDao.create(fieldsIterator.next(), Boolean.parseBoolean(fieldsIterator.next()))
            );
        }
        return answers;
    }


}
