import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.spring01.dao.AnswerDao;
import ru.otus.spring.spring01.dao.AnswerDaoImpl;
import ru.otus.spring.spring01.model.Answer;
import ru.otus.spring.spring01.repository.AnswerRepository;
import ru.otus.spring.spring01.repository.AnswerRepositoryImpl;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@DisplayName("Answer repository test")
public class AnswerRepositoryTest {
    @Test
    @DisplayName("Answers make test")
    void answersMakeTest(){
        AnswerDao answerDao=new AnswerDaoImpl();
        AnswerRepository answerRepository=new AnswerRepositoryImpl(answerDao);
        String[] fields={"answer1","true","answer2","false","answer3","false"};
        List<Answer> answers=answerRepository.getAnswersFromArray(fields);
        assertEquals(3,answers.size());
    }
    @Test
    @DisplayName("Answers fields test")
    void answersFieldsSetTest(){
        AnswerDao answerDao=new AnswerDaoImpl();
        AnswerRepository answerRepository=new AnswerRepositoryImpl(answerDao);
        String[] fields={"answer1","true","answer2","false"};
        List<Answer> answers=answerRepository.getAnswersFromArray(fields);
        assertEquals("answer1",answers.get(0).getName());
        assertEquals(true,answers.get(0).getCondition());
        assertEquals("answer2",answers.get(1).getName());
        assertEquals(false,answers.get(1).getCondition());
    }

}
