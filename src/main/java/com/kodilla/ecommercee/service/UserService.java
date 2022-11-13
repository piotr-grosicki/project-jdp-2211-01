package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.AuthDto;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.UserDao;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Enumeration;

@Service
@RequiredArgsConstructor
public class UserService {

    public static final String BEARER_ = "Bearer ";
    private final UserMapper userMapper;
    private final UserDao userDao;

    private final HttpServletRequest request;

    @Value("${application.security.jwt.secret}")
    protected String SECRET_KEY;
    @Value("${application.security.jwt.expired-time}")
    protected Long EXPIRED_TIME;

    public void createUser(UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userDao.save(user);
    }

    public void blockUser(long id) throws UserNotFoundException {
        User user = userDao.findById(id).orElseThrow(UserNotFoundException::new);
        user.setActive(false);
        userDao.save(user);
    }

    public ResponseEntity<String> generateToken(AuthDto authDto) {
        User user = userDao.findByLogin(authDto.getLogin()).orElse(null);
        Date dateNow = Date.from(Instant.now());
        if (user != null && user.getPassword().equals(authDto.getPassword())) {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
            Key key = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
            return ResponseEntity.ok(
                    Jwts.builder()
                            .setSubject(user.getLogin())
                            .setIssuedAt(dateNow)
                            .setExpiration(Date.from(dateNow.toInstant().plusSeconds(EXPIRED_TIME)))
                            .claim("roles", "user")
                            .signWith(key).compact());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public void authorize() {
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }
        String authorization = request.getHeader("authorization");
        if (authorization.startsWith(BEARER_)) {
            authorization = authorization.substring(BEARER_.length());
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
            JwtParser jwtParser = Jwts.parserBuilder()
                    .setSigningKey(apiKeySecretBytes)
                    .build();

            try {
                jwtParser.parse(authorization);
            } catch (Exception exception) {
                throw new JwtException("");
            }
        }
    }
}
