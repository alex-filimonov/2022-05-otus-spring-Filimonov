package ru.otus.spring.spring02.controller;

import org.springframework.stereotype.Service;
import ru.otus.spring.spring02.dto.AnswerDto;
import ru.otus.spring.spring02.dto.UserDto;
import ru.otus.spring.spring02.service.ExamDataService;

@Service
public class ExamControllerImpl implements ExamController {

    private ExamDataService examDataService;

    @Override
    public void setExamDataService(ExamDataService examDataService){
        this.examDataService=examDataService;
    }
    @Override
    public void userSet(UserDto userDto){
        examDataService.createUser(userDto);
    }
    @Override
    public void userAnswer(AnswerDto answerDto){
        examDataService.addUserAnswer(answerDto);
    }

}
