import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.spring02.dao.AnswerDao;
import ru.otus.spring.spring02.dao.AnswerDaoImpl;
import ru.otus.spring.spring02.dao.QuestionDao;
import ru.otus.spring.spring02.dao.QuestionDaoImpl;
import ru.otus.spring.spring02.model.Answer;
import ru.otus.spring.spring02.model.Question;
import ru.otus.spring.spring02.repository.AnswerRepository;
import ru.otus.spring.spring02.repository.AnswerRepositoryImpl;
import ru.otus.spring.spring02.repository.QuestionRepository;
import ru.otus.spring.spring02.repository.QuestionRepositoryImpl;
import ru.otus.spring.spring02.service.QuestionService;
import ru.otus.spring.spring02.service.QuestionServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Question service test")
public class QuestionServiceTest {

    private QuestionService questionService;
    private QuestionRepository questionRepository;
    private QuestionDao questionDao;
    private AnswerRepository answerRepository;
    private AnswerDao answerDao;

    @Test
    @DisplayName("get all question test")
    public void getAllQuestionTest(){
        List<String> lines=new ArrayList<>();
        lines.add("1;question1;answer1_1;true;answer1_2;false");
        lines.add("2;question2;answer2_1;true;answer2_2;false;answer2_3;false");
        questionDao=new QuestionDaoImpl();
        answerDao=new AnswerDaoImpl();
        answerRepository=new AnswerRepositoryImpl(answerDao);
        questionRepository=new QuestionRepositoryImpl(questionDao,answerRepository);
        questionService=new QuestionServiceImpl(questionRepository);
        List<Question> questions=questionService.getAllQuestions(lines);
        assertEquals(1,questions.get(0).getId());
        assertEquals("question1",questions.get(0).getName());
        assertEquals("answer1_1",questions.get(0).getAnswerList().get(0).getName());
        assertEquals(true,questions.get(0).getAnswerList().get(0).getCondition());
        assertEquals(1,questions.get(0).getAnswerList().get(0).getNumber());
        assertEquals("answer1_2",questions.get(0).getAnswerList().get(1).getName());
        assertEquals(false,questions.get(0).getAnswerList().get(1).getCondition());
        assertEquals(2,questions.get(0).getAnswerList().get(1).getNumber());
        assertEquals(2,questions.get(1).getId());
        assertEquals("question2",questions.get(1).getName());
        assertEquals("answer2_1",questions.get(1).getAnswerList().get(0).getName());
        assertEquals(true,questions.get(1).getAnswerList().get(0).getCondition());
        assertEquals("answer2_2",questions.get(1).getAnswerList().get(1).getName());
        assertEquals(false,questions.get(1).getAnswerList().get(1).getCondition());
        assertEquals("answer2_3",questions.get(1).getAnswerList().get(2).getName());
        assertEquals(false,questions.get(1).getAnswerList().get(2).getCondition());
    }

    @Test
    @DisplayName("get answer from question and number answer")
    public void getAnswerFromQuestionAndNumberAnswerTest(){
        List<String> lines=new ArrayList<>();
        lines.add("1;question1;answer1_1;true;answer1_2;false");
        lines.add("2;question2;answer2_1;true;answer2_2;false;answer2_3;false");
        questionDao=new QuestionDaoImpl();
        answerDao=new AnswerDaoImpl();
        answerRepository=new AnswerRepositoryImpl(answerDao);
        questionRepository=new QuestionRepositoryImpl(questionDao,answerRepository);
        questionService=new QuestionServiceImpl(questionRepository);
        List<Question> questions=questionService.getAllQuestions(lines);
        Answer answer=questionService.getAnswerFromQuestionAndNumberAnswer(questions.get(1),2);
        assertEquals("answer2_2",answer.getName());
        assertEquals(false,answer.getCondition());

    }

}
