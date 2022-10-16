package ru.otus.spring.spring14.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.spring14.model.StarMDB;

@Repository
public interface StarMDBRepository extends MongoRepository<StarMDB,String> {

}
