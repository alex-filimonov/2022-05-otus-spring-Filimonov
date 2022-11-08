package ru.otus.spring.spring18.feing;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "StoreClient", contextId = "StoreClient",url = "http://127.0.0.1:8080")
public interface StoreClient {

    @RequestMapping(method = RequestMethod.GET, value = "/store/{key}")
    String get(@PathVariable("key") String key);

    @RequestMapping(method = RequestMethod.POST, value = "/store/{key}")
    String post(@PathVariable("key") String key,@RequestBody String value);

    @RequestMapping(method = RequestMethod.PUT, value = "/store/{key}")
    String put(@PathVariable("key") String key,@RequestBody String value);

    @RequestMapping(method = RequestMethod.DELETE, value = "/store/{key}")
    String delete(@PathVariable("key") String key);

}
