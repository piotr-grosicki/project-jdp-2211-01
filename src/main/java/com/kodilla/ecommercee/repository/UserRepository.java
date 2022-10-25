package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    User save(User user);


    Optional<User> findById(long id);
}
