package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entities.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface GroupDao extends CrudRepository<Group, Long> {
    @Override
    Group save(Group group);

    Optional<Group> findById(Long id);
}
