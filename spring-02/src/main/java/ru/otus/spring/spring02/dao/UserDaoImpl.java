package ru.otus.spring.spring02.dao;

import org.springframework.stereotype.Service;
import ru.otus.spring.spring02.model.User;

import java.util.ArrayList;

@Service
public class UserDaoImpl implements UserDao {

    public User create(String name){
        return new User(name,new ArrayList<>());
    }
}
