import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.spring02.dao.*;
import ru.otus.spring.spring02.dto.AnswerDto;
import ru.otus.spring.spring02.dto.UserDto;
import ru.otus.spring.spring02.enums.ExamResult;
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

@DisplayName("Exam data service test")
public class ExamDataServiceTest {

    @Test
    @DisplayName("create user test")
    public void createUserTest(){
        UserDao userDao=new UserDaoImpl();
        UserAnswerDao userAnswerDao=new UserAnswerDaoImpl();
        QuestionDao questionDao=new QuestionDaoImpl();
        AnswerDao answerDao=new AnswerDaoImpl();
        AnswerRepository answerRepository=new AnswerRepositoryImpl(answerDao);
        QuestionRepository questionRepository=new QuestionRepositoryImpl(questionDao,answerRepository);
        QuestionService questionService=new QuestionServiceImpl(questionRepository);
        ExamDataService examDataService=new ExamDataServiceImpl(questionService,userDao,userAnswerDao);
        UserDto userDto=new UserDto("Alex");
        examDataService.createUser(userDto);
        assertEquals("Alex",examDataService.getUser().getName());
    }

    @Test
    @DisplayName("add user answer test")
    public void addUserAnswerTest(){
        UserDao userDao=new UserDaoImpl();
        UserAnswerDao userAnswerDao=new UserAnswerDaoImpl();
        QuestionDao questionDao=new QuestionDaoImpl();
        AnswerDao answerDao=new AnswerDaoImpl();
        AnswerRepository answerRepository=new AnswerRepositoryImpl(answerDao);
        QuestionRepository questionRepository=new QuestionRepositoryImpl(questionDao,answerRepository);
        QuestionService questionService=new QuestionServiceImpl(questionRepository);
        ExamDataService examDataService=new ExamDataServiceImpl(questionService,userDao,userAnswerDao);
        UserDto userDto=new UserDto("Alex");
        examDataService.createUser(userDto);

        Question question=questionDao.create(1,"question1");
        List<Answer> answers = new ArrayList<>();
        answers.add(answerDao.create(1,"answer1",true));
        answers.add(answerDao.create(2,"answer2",false));
        question.setAnswerList(answers);

        AnswerDto answerDto=new AnswerDto(question,1);
        examDataService.addUserAnswer(answerDto);
        assertEquals(true,examDataService.getUserAnswers().get(0).getAnswer().getCondition());
    }

    @Test
    @DisplayName("result exam test")
    public void getResultExamTest(){
        UserDao userDao=new UserDaoImpl();
        UserAnswerDao userAnswerDao=new UserAnswerDaoImpl();
        QuestionDao questionDao=new QuestionDaoImpl();
        AnswerDao answerDao=new AnswerDaoImpl();
        AnswerRepository answerRepository=new AnswerRepositoryImpl(answerDao);
        QuestionRepository questionRepository=new QuestionRepositoryImpl(questionDao,answerRepository);
        QuestionService questionService=new QuestionServiceImpl(questionRepository);
        ExamDataService examDataService=new ExamDataServiceImpl(questionService,userDao,userAnswerDao);
        UserDto userDto=new UserDto("Alex");
        examDataService.createUser(userDto);

        Question question1=questionDao.create(1,"question1");
        List<Answer> answers = new ArrayList<>();
        answers.add(answerDao.create(1,"answer1",true));
        answers.add(answerDao.create(2,"answer2",false));
        question1.setAnswerList(answers);
        AnswerDto answerDto1=new AnswerDto(question1,1);
        examDataService.addUserAnswer(answerDto1);

        Question question2=questionDao.create(2,"question2");
        question2.setAnswerList(answers);
        AnswerDto answerDto2=new AnswerDto(question2,2);
        examDataService.addUserAnswer(answerDto2);

        Question question3=questionDao.create(3,"question3");
        question3.setAnswerList(answers);
        AnswerDto answerDto3=new AnswerDto(question3,1);
        examDataService.addUserAnswer(answerDto3);

        assertEquals(ExamResult.PASSED,examDataService.getResultExam(examDataService.getUser().getUserAnswers(),2));
        assertEquals(ExamResult.NOT_PASS,examDataService.getResultExam(examDataService.getUser().getUserAnswers(),3));
    }

}
