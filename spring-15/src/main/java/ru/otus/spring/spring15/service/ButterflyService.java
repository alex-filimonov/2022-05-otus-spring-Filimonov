package ru.otus.spring.spring15.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.spring15.domain.Caterpillar;
import ru.otus.spring.spring15.domain.MetamorphosProduct;
import ru.otus.spring.spring15.domain.SilkCocoon;

@Service
public class ButterflyService {
    public SilkCocoon butterflyOut (MetamorphosProduct metamorphosProduct){
        System.out.println(String.format("butterfly name %s - fly fly",metamorphosProduct.getButterfly().getName()));
        System.out.println(String.format("butterfly name %s - left cocoon %f (weight, g)",metamorphosProduct.getButterfly().getName(),metamorphosProduct.getSilkCocoon().getWeight()));
        return metamorphosProduct.getSilkCocoon();
    }

}
