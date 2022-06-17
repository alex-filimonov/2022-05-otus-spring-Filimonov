import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.spring03.model.Answer;
import ru.otus.spring.spring03.model.Question;
import ru.otus.spring.spring03.repository.AnswerRepository;
import ru.otus.spring.spring03.repository.QuestionRepositoryImpl;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class QuestionRepositoryTest {

    @MockBean
    private AnswerRepository answerRepository;

    @InjectMocks
    private QuestionRepositoryImpl questionRepository;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Question make test")
    public void questionsMakeTest(){
        Mockito.when(answerRepository.getAnswersFromArray(any(String[].class)))
                .thenReturn(Arrays.asList(
                        new Answer(1,"answer1_1",true),
                        new Answer(2,"answer1_2",false)
                ));
        List<String>questionLines= Arrays.asList("1;question1;answer1_1;true;answer1_2;false","2;question2;answer2_1;true;answer2_2;false;answer2_3;false");
        List<Question> questions=questionRepository.getQuestionByStringList(questionLines);
        assertEquals(2,questions.size());
    }

}
