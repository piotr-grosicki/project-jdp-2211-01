package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {


    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUser(final long id) throws UserNotFoundException {
        return userDao.findById(id).orElseThrow(UserNotFoundException::new);
    }

}