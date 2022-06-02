import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.spring02.dao.AnswerDao;
import ru.otus.spring.spring02.dao.AnswerDaoImpl;
import ru.otus.spring.spring02.model.Answer;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Answer DAO test")
public class AnswerDaoTest {
    AnswerDao answerDao=new AnswerDaoImpl();
    @Test
    @DisplayName("Create simple answer")
    void testCreateAnswer(){
        Answer answer=answerDao.create(1,"answer context",true);
        assertEquals("answer context",answer.getName());
        assertEquals(true,answer.getCondition());
    }

}
