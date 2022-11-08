package ru.otus.spring.spring18.service;

public interface StoreService {
    String get(String key);

    void put(String key, String value);

    void delete(String key);
}
