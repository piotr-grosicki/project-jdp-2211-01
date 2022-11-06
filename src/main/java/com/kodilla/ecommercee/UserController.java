package com.kodilla.ecommercee;


import com.kodilla.ecommercee.domain.AuthDto;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.entities.User;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.UserDao;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.KeyPair;
import java.time.Instant;
import java.util.Date;


@AllArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {


    private final UserMapper userMapper;

    private final UserDao userDao;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userDao.save(user);
        return ResponseEntity.ok(null);
    }


    @PatchMapping(value = "/{userID}/blockUser")
    public ResponseEntity<Void> blockUser(@PathVariable long userID) throws UserNotFoundException {
        User user = userDao.findById(userID).orElseThrow(UserNotFoundException::new);
        user.setActive(false);
        userDao.save(user);
        return ResponseEntity.ok(null);
    }


    @GetMapping(value = "/generateToken")
    public ResponseEntity<String> generateToken(@RequestBody AuthDto authDto) {
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
