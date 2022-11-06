package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.AuthDto;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.repository.UserDao;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.kodilla.ecommercee.mapper.UserMapper;

import java.security.KeyPair;
import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserDao userDao;



    public void createUser(UserDto userDto){
        User user = userMapper.mapToUser(userDto);
        userDao.save(user);
    }
    public void blockUser(long userID) throws UserNotFoundException{
        User user = userDao.findById(userID).orElseThrow(UserNotFoundException::new);
        user.setActive(false);
        userDao.save(user);
    }
    public ResponseEntity<String> generateToken(AuthDto authDto){
        User user = userDao.findByLogin(authDto.getLogin()).orElse(null);
        Date dateNow = Date.from(Instant.now());
        if (user!=null && user.getPassword().equals(authDto.getPassword())) {
            KeyPair keys = Keys.keyPairFor(SignatureAlgorithm.ES512);
            return ResponseEntity.ok(Jwts.builder()
                    .setSubject(user.getLogin())
                    .setIssuedAt(dateNow)
                    .setExpiration(Date.from(dateNow.toInstant().plusSeconds(3600)))
                    .claim("roles", "user")
                    .signWith(keys.getPrivate()).compact());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
