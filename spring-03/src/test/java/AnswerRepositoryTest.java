import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.spring03.model.Answer;
import ru.otus.spring.spring03.repository.AnswerRepositoryImpl;
import ru.otus.spring.spring03.repository.QuestionRepositoryImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)

public class AnswerRepositoryTest {

    @InjectMocks
    private AnswerRepositoryImpl answerRepository;

    @Test
    @DisplayName("Answers make test")
    void answersMakeTest(){
        String[] fields={"answer1","true","answer2","false","answer3","false"};
        List<Answer> answers=answerRepository.getAnswersFromArray(fields);
        assertEquals(3,answers.size());
    }

    @Test
    @DisplayName("Answers fields test")
    void answersFieldsSetTest(){
        String[] fields={"answer1","true","answer2","false"};
        List<Answer> answers=answerRepository.getAnswersFromArray(fields);
        assertEquals("answer1",answers.get(0).getName());
        assertEquals(true,answers.get(0).getCondition());
        assertEquals("answer2",answers.get(1).getName());
        assertEquals(false,answers.get(1).getCondition());
    }


}
