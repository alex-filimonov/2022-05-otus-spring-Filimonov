package ru.otus.spring.spring18.service.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.spring18.feing.StoreClient;

@Service
public class StoreClientService {

    @Autowired
    StoreClient storeClient;

    @HystrixCommand(fallbackMethod = "defaultStore")
    public String get(String key) {
        return storeClient.get(key);
    }

    @HystrixCommand(fallbackMethod = "defaultPostStore")
    public void post(String key, String value) {
        storeClient.post(key,value);
    }

    @HystrixCommand(fallbackMethod = "defaultPostStore")
    public void put(String key, String value) {
        storeClient.put(key,value);
    }

    @HystrixCommand(fallbackMethod = "defaultPostStore")
    public void delete(String key) {
        storeClient.delete(key);
    }

    private String defaultStore(String key) {
        return "not valid service";
    }

    private void defaultPostStore(String key, String value) {
        System.out.println("NOT VALID Service");
    }

}
