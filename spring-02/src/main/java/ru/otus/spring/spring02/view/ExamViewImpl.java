package ru.otus.spring.spring02.view;

import org.springframework.stereotype.Service;
import ru.otus.spring.spring02.dto.AnswerDto;
import ru.otus.spring.spring02.dto.UserDto;
import ru.otus.spring.spring02.enums.ExamResult;
import ru.otus.spring.spring02.model.Question;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class ExamViewImpl implements ExamView {

    private static String ENTER_NAME = "Enter you name: ";
    private static String START_EXAM = "Start exam:";
    private static String FINISH_EXAM = "Exam finished";
    private static String HORIZONTAL_LINE = "--------------------------------------------------------------";
    private static String QUESTION_MESSAGE = "Question: ";
    private static String ANSWER_OPTIONS_MESSAGE = "Answer options:";
    private static String ANSWER_OPTIONS_FORMAT = "%d - %s %n";
    private static String ENTER_THE_NUMBER_OF_THE_CORRECT_ANSWER_MESSAGE = "Enter the number of the correct answer";
    private static String EXAM_RESULT_FOR_MESSAGE = "Exam result for %s %n";

    @Override
    public UserDto userSet() {
        System.out.println(ENTER_NAME);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return new UserDto(reader.readLine());
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    @Override
    public void startExamMessage() {
        System.out.println(START_EXAM);
        System.out.println(HORIZONTAL_LINE);
    }

    @Override
    public AnswerDto userAnswerForm(Question question) {
        System.out.println(QUESTION_MESSAGE);
        System.out.println(question.getName());
        System.out.println(ANSWER_OPTIONS_MESSAGE);
        question.getAnswerList().forEach(answer -> {
            System.out.printf(ANSWER_OPTIONS_FORMAT, answer.getNumber(), answer.getName());
        });
        System.out.println(ENTER_THE_NUMBER_OF_THE_CORRECT_ANSWER_MESSAGE);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            AnswerDto answerDto = new AnswerDto(question, Integer.parseInt(reader.readLine()));
            return answerDto;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return new AnswerDto(question, 0);
    }

    @Override
    public void endExamMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(FINISH_EXAM);
    }

    @Override
    public void resultExamMessage(String userName, ExamResult examResult) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(HORIZONTAL_LINE);
        System.out.printf(EXAM_RESULT_FOR_MESSAGE, userName);
        System.out.println(examResult.name());
        System.out.println(HORIZONTAL_LINE);
        System.out.println(HORIZONTAL_LINE);
    }


}
