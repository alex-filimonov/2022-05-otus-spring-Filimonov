package ru.otus.spring.spring04;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.spring.spring04.model.Question;
import ru.otus.spring.spring04.service.CSVResourceService;
import ru.otus.spring.spring04.service.ExamDataService;
import ru.otus.spring.spring04.service.ExamServiceImpl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT + ".enabled=false"
})
public class ExamServiceTest {

    @MockBean
    private ExamDataService examDataService;

    @MockBean
    private CSVResourceService csvResourceService;


    @InjectMocks
    private ExamServiceImpl examService;


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
        Mockito.when(csvResourceService.getLineListByResourceStream(any(Boolean.class),any(InputStream.class)))
                .thenReturn(Arrays.asList("1;Question1;ans1;true"));
        List<String> str=csvResourceService.getLineListByResourceStream(true, (InputStream) Stream.empty());
        assertEquals(1,str.size());

    }

}
