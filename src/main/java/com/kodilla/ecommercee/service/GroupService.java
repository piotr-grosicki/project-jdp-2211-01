package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.entity.Group;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.repository.GroupDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupDao groupDao;

    @Transactional
    public Group create(Group group) {
        if (group.getId() != null) {
            throw new IllegalArgumentException();
        }

        groupDao.save(group);

        return group;
    }

    @Transactional
    public Group update(Group group) throws GroupNotFoundException {
        if (!groupDao.existsById(group.getId())) {
            throw new GroupNotFoundException();
        }

        groupDao.save(group);

        return group;
    }

    public Group getGroup(Long id) throws GroupNotFoundException {
        Optional<Group> group = groupDao.findById(id);
        if (!group.isPresent()) {
            throw new GroupNotFoundException();
        }

        return group.get();
    }

    public List<Group> getAll() {
        return groupDao.findAll();
    }
}
