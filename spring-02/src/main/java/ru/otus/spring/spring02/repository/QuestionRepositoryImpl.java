package ru.otus.spring.spring02.repository;

import org.springframework.stereotype.Component;
import ru.otus.spring.spring02.model.Answer;
import ru.otus.spring.spring02.model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class QuestionRepositoryImpl implements QuestionRepository {

    private AnswerRepository answerRepository;
    private static final String CSV_DELIMITER = ";";

    public QuestionRepositoryImpl( AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public List<Question> getQuestionByStringList(List<String> lines) {
        List<Question> questionList = new ArrayList<>();
        lines.forEach(line -> {
            String[] fields = line.split(CSV_DELIMITER);
            if (fields.length > 1) {
                Question question = new Question(Integer.parseInt(fields[0]), fields[1]);
                question.setAnswerList(answerRepository.getAnswersFromArray(Arrays.copyOfRange(fields, 2, fields.length)));
                questionList.add(question);
            }
        });
        return questionList;
    }

    @Override
    public Answer getAnswerFromQuestionAndNumberAnswer(Question question, int number) {
        return answerRepository.getAnswerByNumber(question.getAnswerList(), number);
    }

}
