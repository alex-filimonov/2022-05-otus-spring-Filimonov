import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.spring02.dao.AnswerDao;
import ru.otus.spring.spring02.dao.AnswerDaoImpl;
import ru.otus.spring.spring02.dao.QuestionDao;
import ru.otus.spring.spring02.dao.QuestionDaoImpl;
import ru.otus.spring.spring02.model.Question;
import ru.otus.spring.spring02.repository.AnswerRepository;
import ru.otus.spring.spring02.repository.AnswerRepositoryImpl;
import ru.otus.spring.spring02.repository.QuestionRepository;
import ru.otus.spring.spring02.repository.QuestionRepositoryImpl;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Question repository test")
public class QuestionRepositoryTest {

    @Test
    @DisplayName("Question make test")
    public void questionsMakeTest(){
        AnswerDao answerDao=new AnswerDaoImpl();
        AnswerRepository answerRepository=new AnswerRepositoryImpl(answerDao);
        QuestionDao questionDao=new QuestionDaoImpl();
        List<String>questionLines= Arrays.asList("1;question1;answer1_1;true;answer1_2;false","2;question2;answer2_1;true;answer2_2;false;answer2_3;false");
        QuestionRepository questionRepository=new QuestionRepositoryImpl(questionDao,answerRepository);
        List<Question> questions=questionRepository.getQuestionByStringList(questionLines);
        assertEquals(2,questions.size());
        assertEquals(2,questions.get(0).getAnswerList().size());
        assertEquals(3,questions.get(1).getAnswerList().size());
    }

    @Test
    @DisplayName("Question fields set test")
    public void questionsFieldsSetTest(){
        AnswerDao answerDao=new AnswerDaoImpl();
        AnswerRepository answerRepository=new AnswerRepositoryImpl(answerDao);
        QuestionDao questionDao=new QuestionDaoImpl();
        List<String>questionLines= Arrays.asList("1;question1;answer1_1;true;answer1_2;false","2;question2;answer2_1;true;answer2_2;false;answer2_3;false");
        QuestionRepository questionRepository=new QuestionRepositoryImpl(questionDao,answerRepository);
            List<Question> questions=questionRepository.getQuestionByStringList(questionLines);
            assertEquals(1,questions.get(0).getId());
            assertEquals("question1",questions.get(0).getName());
            assertEquals("answer1_1",questions.get(0).getAnswerList().get(0).getName());
            assertEquals(true,questions.get(0).getAnswerList().get(0).getCondition());
            assertEquals("answer1_2",questions.get(0).getAnswerList().get(1).getName());
            assertEquals(false,questions.get(0).getAnswerList().get(1).getCondition());
            assertEquals(2,questions.get(1).getId());
            assertEquals("question2",questions.get(1).getName());
            assertEquals("answer2_1",questions.get(1).getAnswerList().get(0).getName());
            assertEquals(true,questions.get(1).getAnswerList().get(0).getCondition());
            assertEquals("answer2_2",questions.get(1).getAnswerList().get(1).getName());
            assertEquals(false,questions.get(1).getAnswerList().get(1).getCondition());
            assertEquals("answer2_3",questions.get(1).getAnswerList().get(2).getName());
            assertEquals(false,questions.get(1).getAnswerList().get(2).getCondition());
    }


}
