package ru.otus.spring.spring18.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StoreServiceImpl implements StoreService {
    private Map<String,String> store;

    public StoreServiceImpl(){
        store=new HashMap<>();
    }

    @Override
    public String get(String key){
        if (store.containsKey(key)){
            return store.get(key);
        }
        return null;
    }

    @Override
    public void put(String key, String value){
        store.put(key,value);
    }

    @Override
    public void delete(String key){
        store.remove(key);
    }

}
