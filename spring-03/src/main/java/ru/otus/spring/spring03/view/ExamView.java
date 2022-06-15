package ru.otus.spring.spring03.view;

import ru.otus.spring.spring03.dto.AnswerDto;
import ru.otus.spring.spring03.dto.UserDto;
import ru.otus.spring.spring03.enums.ExamResult;
import ru.otus.spring.spring03.model.Question;

public interface ExamView {
    UserDto userSet();

    void startExamMessage();

    AnswerDto userAnswerForm(Question question);

    void endExamMessage();

    void resultExamMessage(String userName, ExamResult examResult);
}
