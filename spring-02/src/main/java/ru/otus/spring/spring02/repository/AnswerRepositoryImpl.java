package ru.otus.spring.spring02.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.otus.spring.spring02.dao.AnswerDao;
import ru.otus.spring.spring02.model.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Repository
public class AnswerRepositoryImpl implements AnswerRepository {
    private final AnswerDao answerDao;


    public AnswerRepositoryImpl(AnswerDao answerDao) {
        this.answerDao = answerDao;
    }

    public List<Answer> getAnswersFromArray(String[] fields) {
        List<Answer> answers = new ArrayList<>();
        Iterator<String> fieldsIterator = Arrays.stream(fields).iterator();
        int number=1;
        while (fieldsIterator.hasNext()) {
            answers.add(
                    answerDao.create(number,fieldsIterator.next(), Boolean.parseBoolean(fieldsIterator.next()))
            );
            number++;
        }
        return answers;
    }

    public Answer getAnswerByNumber(List<Answer> answers,int number){
        try {
            return answers.get(number-1);
        } catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }


}
