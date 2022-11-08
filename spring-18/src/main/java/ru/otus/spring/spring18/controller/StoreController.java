package ru.otus.spring.spring18.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.spring18.service.StoreService;

@RestController
public class StoreController {

    @Autowired
    StoreService storeService;

    @GetMapping("/store/{key}")
    public String get(@PathVariable("key") String key){
        return storeService.get(key);
    }

    @PostMapping("/store/{key}")
    public void post(@PathVariable("key") String key,@RequestBody String value){
        storeService.put(key,value);
    }

    @PutMapping("/store/{key}")
    public void put(@PathVariable("key") String key,@RequestBody String value){
        storeService.put(key,value);
    }

    @DeleteMapping("/store/{key}")
    public void delete(@PathVariable("key") String key){
        storeService.delete(key);
    }


}
