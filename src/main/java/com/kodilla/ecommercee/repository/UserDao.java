package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDao extends CrudRepository<User, Long> {
    @Override
    User save(User user);

    Optional<User> findByLogin(String login);

    Optional<User> findById(long id);
}
