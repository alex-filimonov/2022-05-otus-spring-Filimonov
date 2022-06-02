package ru.otus.spring.spring02.controller;

import ru.otus.spring.spring02.dto.AnswerDto;
import ru.otus.spring.spring02.dto.UserDto;
import ru.otus.spring.spring02.service.ExamDataService;

public interface ExamController {
    void userSet(UserDto userDto);
    void setExamDataService(ExamDataService examDataService);
    void userAnswer(AnswerDto answerDto);
}
