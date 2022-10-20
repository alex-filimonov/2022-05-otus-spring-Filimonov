package ru.otus.spring.spring15.shell;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.spring15.config.SilkEndpoint;
import ru.otus.spring.spring15.domain.Caterpillar;
import ru.otus.spring.spring15.domain.SilkCocoon;
import ru.otus.spring.spring15.enums.CaterpillarTypeEnum;

import java.util.*;
import java.util.stream.Collectors;

@ShellComponent
public class ConsoleShell {

    private static List<String> names= Arrays.asList("Amelia","Alisa","Bella","Zuzu","Scilla","Ziza","Feola","Kata","Alex","Helena");

    @Autowired
    SilkEndpoint silkEndpoint;

    @ShellMethod("start")
    public void start(int count){
        if (count>names.size()){
            System.out.println(String.format("count max - %d",names.size()));
            return;
        }
        List<Caterpillar> caterpillars=new ArrayList<>();
        for (int i=0;i<count;i++){
            caterpillars.add(new Caterpillar(names.get(i),randomEnum(CaterpillarTypeEnum.class)));
        }

       Collection<SilkCocoon> cocoons=silkEndpoint.process(caterpillars);
       cocoons.forEach(cocoon ->{
           System.out.println(String.format("Take cocoon. weight %f", cocoon.getWeight()));
       });

    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = new Random().nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
