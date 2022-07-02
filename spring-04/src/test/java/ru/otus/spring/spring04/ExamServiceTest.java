package ru.otus.spring.spring04;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.spring.spring04.config.AppConfig;
import ru.otus.spring.spring04.dto.AnswerDto;
import ru.otus.spring.spring04.enums.ExamResult;
import ru.otus.spring.spring04.model.Answer;
import ru.otus.spring.spring04.model.Question;
import ru.otus.spring.spring04.model.User;
import ru.otus.spring.spring04.model.UserAnswer;
import ru.otus.spring.spring04.service.CSVResourceService;
import ru.otus.spring.spring04.service.ExamDataService;
import ru.otus.spring.spring04.service.ExamDataServiceImpl;
import ru.otus.spring.spring04.service.ExamServiceImpl;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT + ".enabled=false"
})
public class ExamServiceTest {


    @MockBean
    private ExamDataService examDataService;
    @MockBean
    private CSVResourceService csvResourceService;

    @MockBean
    private AppConfig appConfig;

    @MockBean
    private MessageSource messageSource;


    @InjectMocks
    private ExamServiceImpl examService;


    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @DisplayName("Set user test")
    void setUserTest(){
        examService.setUser("Alex");
        assertEquals("Alex",examService.getUserName());
        assertEquals(0,examService.getUser().getUserAnswers().size());
    }

    @Test
    @DisplayName("load question Test")
    void loadQuestionTest(){
        MockitoAnnotations.initMocks(this);
        InputStream empty = new InputStream() {
            @Override
            public int read() {
                return -1;
            }
        };
        Mockito.when(csvResourceService.getLineListByResourceStream(any(Boolean.class),any(InputStream.class)))
                .thenReturn(Arrays.asList("1;Question1;ans1;true"));
        Mockito.doReturn(Arrays.asList(new Question(1,"Question1"))).when(examDataService).getAllQuestions(any(List.class));
        examService.loadQuestion();
        assertEquals(1,examService.getQuestionList().size());

    }


    @Test
    @DisplayName("get next question Test")
    void getNextQuestionTest(){
        MockitoAnnotations.initMocks(this);
        InputStream empty = new InputStream() {
            @Override
            public int read() {
                return -1;
            }
        };
        Mockito.when(csvResourceService.getLineListByResourceStream(any(Boolean.class),any(InputStream.class)))
                .thenReturn(Arrays.asList("1;Question1;ans1;true"));
        Mockito.doReturn(Arrays.asList(new Question(1,"Question1"))).when(examDataService).getAllQuestions(any(List.class));
        examService.loadQuestion();
        examService.setUser("Alex");
        String questionStr=examService.getNextQuestion();
        assertEquals("Question1",questionStr);
    }

    @Test
    @DisplayName("get answer options Test")
    void getAnswerOptionTest(){
        MockitoAnnotations.initMocks(this);
        InputStream empty = new InputStream() {
            @Override
            public int read() {
                return -1;
            }
        };
        Mockito.when(csvResourceService.getLineListByResourceStream(any(Boolean.class),any(InputStream.class)))
                .thenReturn(Arrays.asList("1;Question1;ans1;true"));
        Question question=new Question(1,"Question1");
        question.setAnswerList(Arrays.asList(new Answer(1,"answer1",true),new Answer(2,"answer2",false)));
        Mockito.doReturn(Arrays.asList(question)).when(examDataService).getAllQuestions(any(List.class));
        examService.loadQuestion();
        examService.setUser("Alex");
        List<String> answerOptionsString=examService.getAnswerOptions();
        assertEquals(2,answerOptionsString.size());
    }

    @Test
    @DisplayName("set answer options Test")
    void setAnswerOptionTest(){
        MockitoAnnotations.initMocks(this);
        InputStream empty = new InputStream() {
            @Override
            public int read() {
                return -1;
            }
        };
        Mockito.when(csvResourceService.getLineListByResourceStream(any(Boolean.class),any(InputStream.class)))
                .thenReturn(Arrays.asList("1;Question1;ans1;true"));
        Question question=new Question(1,"Question1");
        question.setAnswerList(Arrays.asList(new Answer(1,"answer1",true),new Answer(2,"answer2",false)));
        Mockito.doReturn(Arrays.asList(question)).when(examDataService).getAllQuestions(any(List.class));
        Mockito.doReturn(new User("Alex",Arrays.asList(new UserAnswer(question,1,new Answer(1,"ans1",true))))).when(examDataService).addUserAnswer(any(User.class),any(AnswerDto.class));
        Mockito.doReturn("en-EN").when(appConfig).getLocaleName();
        Mockito.doReturn("End exam").when(messageSource).getMessage(any(String.class),any(),any(Locale.class));
        examService.loadQuestion();
        examService.setUser("Alex");
        examService.setAnswerOption(1);
        String questionStr=examService.getNextQuestion();
        assertEquals("End exam",questionStr);
        assertEquals(1,examService.getUser().getUserAnswers().size());

    }

    @Test
    @DisplayName("result Test")
    void resultTest(){
        MockitoAnnotations.initMocks(this);
        InputStream empty = new InputStream() {
            @Override
            public int read() {
                return -1;
            }
        };
        Mockito.when(csvResourceService.getLineListByResourceStream(any(Boolean.class),any(InputStream.class)))
                .thenReturn(Arrays.asList("1;Question1;ans1;true"));
        Question question=new Question(1,"Question1");
        question.setAnswerList(Arrays.asList(new Answer(1,"answer1",true),new Answer(2,"answer2",false)));
        Mockito.doReturn(Arrays.asList(question)).when(examDataService).getAllQuestions(any(List.class));
        Mockito.doReturn(new User("Alex",Arrays.asList(new UserAnswer(question,1,new Answer(1,"ans1",true))))).when(examDataService).addUserAnswer(any(User.class),any(AnswerDto.class));
        Mockito.doReturn(1).when(appConfig).getMinCorrectAnswers();
        Mockito.doReturn(ExamResult.PASSED).when(examDataService).getResultExam(any(List.class),anyInt());
        examService.loadQuestion();
        examService.setUser("Alex");
        examService.setAnswerOption(1);
        String res=examService.getResult();
        assertEquals("PASSED",res);

    }


}
