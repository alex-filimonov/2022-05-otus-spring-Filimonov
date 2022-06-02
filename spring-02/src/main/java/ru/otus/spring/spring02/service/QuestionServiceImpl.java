package ru.otus.spring.spring02.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import ru.otus.spring.spring02.dto.AnswerDto;
import ru.otus.spring.spring02.model.Answer;
import ru.otus.spring.spring02.model.Question;
import ru.otus.spring.spring02.repository.QuestionRepository;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private CSVResourceService csvResourceService;
    private QuestionRepository questionRepository;

    QuestionServiceImpl(CSVResourceService csvResourceService,QuestionRepository questionRepository){
        this.csvResourceService=csvResourceService;
        this.questionRepository=questionRepository;
    }

    @Override
    public List<Question> getAllQuestions(){
        return questionRepository.getQuestionByStringList(
                csvResourceService.getLineListByResourceStream(true, csvResourceService.getCSVResourceStream()));
    }

    public void outputQuestions() {
        List<Question> questions=questionRepository.getQuestionByStringList(
                csvResourceService.getLineListByResourceStream(true, csvResourceService.getCSVResourceStream()));
        questions.forEach(question -> {
            System.out.println(question.getName());
            question.getAnswerList().forEach(answer -> {
                System.out.println("---> " + answer.getName());
            });
        });
    }

    @Override
    public Answer getAnswerFromQuestionAndNumberAnswer(Question question,int number){
        return questionRepository.getAnswerFromQuestionAndNumberAnswer(question,number);
    }

}
