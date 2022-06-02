import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.spring02.dao.*;
import ru.otus.spring.spring02.model.Answer;
import ru.otus.spring.spring02.model.Question;
import ru.otus.spring.spring02.model.User;
import ru.otus.spring.spring02.model.UserAnswer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("User answer DAO test")
public class UserAnswerDaoTest {
    UserAnswerDao userAnswerDao=new UserAnswerDaoImpl();
    QuestionDao questionDao=new QuestionDaoImpl();
    AnswerDao answerDao=new AnswerDaoImpl();

    @Test
    @DisplayName("Create user answer")
    void testCreateUserAnswer(){
        Question question=questionDao.create(1,"question 1");
        List<Answer> answers=new ArrayList<>();
        answers.add(answerDao.create(1,"answer 1",false));
        answers.add(answerDao.create(2,"answer 2",true));
        answers.add(answerDao.create(3,"answer 3",false));
        question.setAnswerList(answers);
        UserAnswer userAnswer=userAnswerDao.create(question,answers.get(1),2);
        assertEquals("question 1",userAnswer.getQuestion().getName());
        assertEquals(3,userAnswer.getQuestion().getAnswerList().size());
        assertEquals("answer 2",userAnswer.getAnswer().getName());
        assertEquals(2,userAnswer.getUserAnswerNumber());
    }


}
