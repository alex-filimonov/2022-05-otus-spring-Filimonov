import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.spring02.dto.AnswerDto;
import ru.otus.spring.spring02.dto.UserDto;
import ru.otus.spring.spring02.enums.ExamResult;
import ru.otus.spring.spring02.model.Answer;
import ru.otus.spring.spring02.model.Question;
import ru.otus.spring.spring02.model.User;
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
    @DisplayName("result exam test")
    public void getResultExamTest(){

        AnswerRepository answerRepository=new AnswerRepositoryImpl();
        QuestionRepository questionRepository=new QuestionRepositoryImpl(answerRepository);
        QuestionService questionService=new QuestionServiceImpl(questionRepository);
        ExamDataService examDataService=new ExamDataServiceImpl(questionService);

        User user=new User("Alex",new ArrayList<>());
        Question question1=new Question(1,"question1");
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(1,"answer1",true));
        answers.add(new Answer(2,"answer2",false));
        question1.setAnswerList(answers);

        AnswerDto answerDto1=new AnswerDto(question1,1);
        user=examDataService.addUserAnswer(user,answerDto1);

        Question question2=new Question(2,"question2");
        question2.setAnswerList(answers);
        AnswerDto answerDto2=new AnswerDto(question2,2);
        user=examDataService.addUserAnswer(user,answerDto2);


        Question question3=new Question(3,"question3");
        question3.setAnswerList(answers);
        AnswerDto answerDto3=new AnswerDto(question3,1);
        user=examDataService.addUserAnswer(user,answerDto3);

        assertEquals(ExamResult.PASSED,examDataService.getResultExam(user.getUserAnswers(),2));
        assertEquals(ExamResult.NOT_PASS,examDataService.getResultExam(user.getUserAnswers(),3));
    }
}
