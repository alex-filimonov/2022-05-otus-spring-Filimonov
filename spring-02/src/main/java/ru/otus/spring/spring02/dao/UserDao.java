package ru.otus.spring.spring02.dao;

import ru.otus.spring.spring02.model.User;

public interface UserDao {
    User create(String name);
}
