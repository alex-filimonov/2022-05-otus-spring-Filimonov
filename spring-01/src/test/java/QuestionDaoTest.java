import org.junit.jupiter.api.DisplayName;
import ru.otus.spring.spring01.dao.QuestionDao;
import ru.otus.spring.spring01.dao.QuestionDaoImpl;
import ru.otus.spring.spring01.model.Question;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

@DisplayName("Question DAO test")
public class QuestionDaoTest {
    QuestionDao questionDao;
    @Test
    @DisplayName("Create simple question")
    void testCreateQuestion(){
        String[] fields={"1","Context question","any fields"};
        questionDao=new QuestionDaoImpl();
        Question question=questionDao.create(fields);
        assertNotNull(question);
        assertEquals(1,question.getId());
        assertEquals("Context question",question.getName());
    }

}
