package ru.otus.spring.spring14.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.spring14.model.Star;
import ru.otus.spring.spring14.model.StarMDB;
import ru.otus.spring.spring14.repository.StarMDBRepository;

import java.util.List;

@Service
public class StarServiceImpl implements StarService {

    private StarMDBRepository starMDBRepository;

    StarServiceImpl(StarMDBRepository starMDBRepository){
        this.starMDBRepository=starMDBRepository;
    }

    @Override
    public StarMDB convertToStartMDB(Star star){
        StarMDB starMDB=new StarMDB();
        starMDB.setName(star.getName());
        starMDB.setDistance(star.getDistance());
        return starMDB;
    }

    @Override
    public List<StarMDB> getAll(){
        return starMDBRepository.findAll();
    }

}
