package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface GroupDao extends CrudRepository<Group, Long> {
    @Override
    Group save(Group group);

    @Override
    Optional<Group> findById(Long id);
}
