package com.kodilla.ecommercee;


import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.entities.User;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.UserDao;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.Date;


@RestController
@RequestMapping("/v1/users")
public class UserController {


    private UserMapper userMapper;

    private UserDao userDao;

    @Autowired
    public UserController(UserMapper userMapper, UserDao userDao) {
        this.userMapper = userMapper;
        this.userDao = userDao;
    }

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

    @GetMapping(value = "/{login}/generateToken")
    public ResponseEntity<String> generateToken(@PathVariable String login, String password) throws UserNotFoundException {
        User user = userDao.findByLogin(login).orElseThrow(UserNotFoundException::new);
        if (user.getPassword().equals(password)) {
            val keys = Keys.keyPairFor(SignatureAlgorithm.ES512);
            return ResponseEntity.ok(Jwts.builder()
                    .setSubject("userid") // 1
                    .setIssuedAt(Date.from(Instant.now()))
                    .setExpiration(Date.from(Instant.now().plusSeconds(3600)))
                    .claim("roles", "user")
                    .signWith(keys.getPrivate()).compact());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}
