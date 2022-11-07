package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface GroupDao extends CrudRepository<Group, Long> {

    Group save(Group group);
    Optional<Group> findById(Long id);
}
