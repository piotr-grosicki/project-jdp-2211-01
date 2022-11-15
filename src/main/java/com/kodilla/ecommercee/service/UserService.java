package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.UserDao;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service

@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoderService passwordEncoderService;
    private final UserMapper userMapper;
    private final UserDao userDao;
    public void createUser(UserDto userDto) throws NoSuchAlgorithmException, IOException {
        User user = userMapper.mapToUser(userDto);

        user.setPassword(passwordEncoderService.encodePassword(user.getPassword()));

        userDao.save(user);
    }

    public User findUser(long id) throws UserNotFoundException {
        return userDao.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public void blockUser(long id) throws UserNotFoundException {
        User user = userDao.findById(id).orElseThrow(UserNotFoundException::new);
        user.setActive(false);
        userDao.save(user);
    }

    public UserDto findUserByLogin(String login) throws UserNotFoundException {
        return userDao.findByLogin(login).map(userMapper::mapToUserDto).orElseThrow(UserNotFoundException::new);
    }
}
