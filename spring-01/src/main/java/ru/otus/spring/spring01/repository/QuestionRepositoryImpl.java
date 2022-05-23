package ru.otus.spring.spring01.repository;

import ru.otus.spring.spring01.dao.QuestionDao;
import ru.otus.spring.spring01.model.Question;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class QuestionRepositoryImpl implements QuestionRepository {

    private QuestionDao questionDao;
    private AnswerRepository answerRepository;
    private InputStream stream;
    private static final String CSV_DELIMITER = ";";

    public QuestionRepositoryImpl(InputStream stream, QuestionDao questionDao, AnswerRepository answerRepository) {
        this.questionDao = questionDao;
        this.answerRepository = answerRepository;
        this.stream = stream;
    }

    public List<Question> getAll() throws IOException {
        Scanner scanner = new Scanner(stream);
        List<Question> questionList = new ArrayList<>();
        for (int lineNum = 1; scanner.hasNext(); lineNum++) {
            if (lineNum == 1) {
                scanner.nextLine();
                continue;
            }
            String line = scanner.nextLine();
            String[] fields = line.split(CSV_DELIMITER);
            if (fields.length > 1) {
                Question question = questionDao.create(fields);
                question.setAnswerList(answerRepository.getAnswersFromArray(Arrays.copyOfRange(fields, 2, fields.length)));
                questionList.add(question);
            }
        }
        ;
        scanner.close();
        return questionList;
    }


}
