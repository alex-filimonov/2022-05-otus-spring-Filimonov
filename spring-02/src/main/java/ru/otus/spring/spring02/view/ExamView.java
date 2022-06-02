package ru.otus.spring.spring02.view;

import ru.otus.spring.spring02.dto.AnswerDto;
import ru.otus.spring.spring02.dto.UserDto;
import ru.otus.spring.spring02.enums.ExamResult;
import ru.otus.spring.spring02.model.Question;

public interface ExamView {
    UserDto userSet();

    void startExamMessage();

    AnswerDto userAnswerForm(Question question);

    void endExamMessage();

    void resultExamMessage(String userName, ExamResult examResult);
}
