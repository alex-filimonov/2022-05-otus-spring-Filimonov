package ru.otus.spring.spring12.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.spring12.domain.AppUser;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    Optional<AppUser> findByLogin(String login);
}
