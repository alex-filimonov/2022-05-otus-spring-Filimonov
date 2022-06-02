import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.spring02.dao.AnswerDao;
import ru.otus.spring.spring02.dao.AnswerDaoImpl;
import ru.otus.spring.spring02.dao.UserDao;
import ru.otus.spring.spring02.dao.UserDaoImpl;
import ru.otus.spring.spring02.model.Answer;
import ru.otus.spring.spring02.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("User DAO test")
public class UserDaoTest {
    UserDao userDao=new UserDaoImpl();

    @Test
    @DisplayName("Create user")
    void testCreateUser(){
        User user = userDao.create("Alex");
        assertEquals("Alex",user.getName());
    }


}
