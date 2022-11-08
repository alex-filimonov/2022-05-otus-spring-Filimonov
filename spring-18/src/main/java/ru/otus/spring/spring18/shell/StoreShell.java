package ru.otus.spring.spring18.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.spring18.service.client.StoreClientService;

@ShellComponent
public class StoreShell {

    @Autowired
    StoreClientService storeClientService;

    @ShellMethod("get")
    public void get(String key){
        System.out.println(storeClientService.get(key));
    }

    @ShellMethod("post")
    public void post(String key, String value){
        storeClientService.post(key,value);
        System.out.println("Ok");
    }
    @ShellMethod("put")
    public void put(String key, String value){
        storeClientService.put(key,value);
        System.out.println("Ok");
    }

    @ShellMethod("delete")
    public void delete(String key){
        storeClientService.delete(key);
        System.out.println("Ok");
    }

}
