package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Long> {

    @Override
    User save(User user);

    Optional<User> findByLogin(String login);
}
