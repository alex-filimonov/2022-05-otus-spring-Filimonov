package ru.otus.spring.spring14.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.otus.spring.spring14.model.Star;

import java.util.List;

public interface StarReaderRepository extends PagingAndSortingRepository<Star,Long> {
    List<Star> findAll();
}
