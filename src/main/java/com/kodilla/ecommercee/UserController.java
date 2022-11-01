package com.kodilla.ecommercee;


import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.entities.User;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.UserDao;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;


@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDao userDao;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userDao.save(user);

        return ResponseEntity.ok().build();
    }


    @PatchMapping(value = "/{userID}/blockUser")
    public ResponseEntity<Boolean> blockUser(@PathVariable long userID) throws UserNotFoundException {
        User user = userDao.findById(userID).orElseThrow(UserNotFoundException::new);
        user.setActive(false);
        userDao.save(user);

        return ResponseEntity.ok(true);


    }

    @PatchMapping(value = "/{userID}/generateToken")
    public String generateToken(@PathVariable long userID) throws UserNotFoundException {
        User user = userDao.findById(userID).orElseThrow(UserNotFoundException::new);
        String SECRET_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2p3dC1pZHAuZXhhbXBsZS5jb20iLCJzdWIiOiJtYWlsdG86bWlrZUBleGFtcGxlLmNvbSIsIm5iZiI6MTY2NzE1NzIyOCwiZXhwIjoxNjY3MTYwODI4LCJpYXQiOjE2NjcxNTcyMjgsImp0aSI6ImlkMTIzNDU2IiwidHlwIjoiaHR0cHM6Ly9leGFtcGxlLmNvbS9yZWdpc3RlciJ9.0hcomVZ_yLmnDpVnJM3g953tciGIUfwrsxGFyt-OoMc";
        long now = System.currentTimeMillis();
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        return Jwts.builder()
               .setSubject(user.getLogin())
               .setIssuedAt(new Date(now))
               .setExpiration(new Date(now +3600))
               .signWith(signingKey,signatureAlgorithm).compact();
    }

}
