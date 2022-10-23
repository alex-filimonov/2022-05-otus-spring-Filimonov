package ru.otus.spring.spring15.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.spring15.domain.Butterfly;
import ru.otus.spring.spring15.domain.Caterpillar;
import ru.otus.spring.spring15.domain.MetamorphosProduct;
import ru.otus.spring.spring15.domain.SilkCocoon;
import ru.otus.spring.spring15.enums.CaterpillarTypeEnum;

@Service
public class CaterpillarService {

    public MetamorphosProduct metamorphosis(Caterpillar caterpillar){
        System.out.println(String.format("Begin metamorphosis caterpillar: %s",caterpillar.getName()));

        Butterfly butterfly=new Butterfly(caterpillar.getName(),caterpillar.getType());
        SilkCocoon cocoon=new SilkCocoon(0);
        if (caterpillar.getType().equals(CaterpillarTypeEnum.SILKWORM)){
            cocoon.setWeight((float) 0.15);
        }
        return new MetamorphosProduct(butterfly,cocoon);
    }

}
