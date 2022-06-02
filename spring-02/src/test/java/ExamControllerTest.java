import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.spring02.controller.ExamController;
import ru.otus.spring.spring02.controller.ExamControllerImpl;
import ru.otus.spring.spring02.dao.*;
import ru.otus.spring.spring02.dto.AnswerDto;
import ru.otus.spring.spring02.dto.UserDto;
import ru.otus.spring.spring02.model.Answer;
import ru.otus.spring.spring02.model.Question;
import ru.otus.spring.spring02.repository.AnswerRepository;
import ru.otus.spring.spring02.repository.AnswerRepositoryImpl;
import ru.otus.spring.spring02.repository.QuestionRepository;
import ru.otus.spring.spring02.repository.QuestionRepositoryImpl;
import ru.otus.spring.spring02.service.ExamDataService;
import ru.otus.spring.spring02.service.ExamDataServiceImpl;
import ru.otus.spring.spring02.service.QuestionService;
import ru.otus.spring.spring02.service.QuestionServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Exam controller test")
public class ExamControllerTest {
    @Test
    @DisplayName("create user test")
    public void setUserTest() {
        UserDao userDao=new UserDaoImpl();
        UserAnswerDao userAnswerDao=new UserAnswerDaoImpl();
        QuestionDao questionDao=new QuestionDaoImpl();
        AnswerDao answerDao=new AnswerDaoImpl();
        AnswerRepository answerRepository=new AnswerRepositoryImpl(answerDao);
        QuestionRepository questionRepository=new QuestionRepositoryImpl(questionDao,answerRepository);
        QuestionService questionService=new QuestionServiceImpl(questionRepository);
        ExamDataService examDataService=new ExamDataServiceImpl(questionService,userDao,userAnswerDao);

        ExamController examController=new ExamControllerImpl();
        examController.setExamDataService(examDataService);
        UserDto userDto=new UserDto("Alex");
        examController.userSet(userDto);
        assertEquals("Alex",examDataService.getUser().getName());
    }

    @Test
    @DisplayName("user answer test")
    public void userAnswerTest(){

        UserDao userDao=new UserDaoImpl();
        UserAnswerDao userAnswerDao=new UserAnswerDaoImpl();
        QuestionDao questionDao=new QuestionDaoImpl();
        AnswerDao answerDao=new AnswerDaoImpl();
        AnswerRepository answerRepository=new AnswerRepositoryImpl(answerDao);
        QuestionRepository questionRepository=new QuestionRepositoryImpl(questionDao,answerRepository);
        QuestionService questionService=new QuestionServiceImpl(questionRepository);
        ExamDataService examDataService=new ExamDataServiceImpl(questionService,userDao,userAnswerDao);

        ExamController examController=new ExamControllerImpl();
        examController.setExamDataService(examDataService);
        UserDto userDto=new UserDto("Alex");
        examController.userSet(userDto);

        Question question=questionDao.create(1,"question1");
        List<Answer> answers = new ArrayList<>();
        answers.add(answerDao.create(1,"answer1",true));
        answers.add(answerDao.create(2,"answer2",false));
        question.setAnswerList(answers);

        AnswerDto answerDto=new AnswerDto(question,1);
        examController.userAnswer(answerDto);
        assertEquals(true,examDataService.getUserAnswers().get(0).getAnswer().getCondition());

    }

}
