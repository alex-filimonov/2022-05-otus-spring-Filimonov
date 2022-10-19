package ru.otus.spring.spring14.service;

import ru.otus.spring.spring14.model.Star;
import ru.otus.spring.spring14.model.StarMDB;

import java.util.List;

public interface StarService {
    StarMDB convertToStartMDB(Star star);

    List<StarMDB> getAll();
}
