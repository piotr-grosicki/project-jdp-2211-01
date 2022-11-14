package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.AuthDto;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.exception.UnauthorizedException;
import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@RequestScope
public class SecurityService {

    public static final String BEARER_ = "Bearer ";
    private final UserService userService;
    private final PasswordEncoderService passwordEncoderService;

    private final HttpServletRequest request;

    @Value("${application.security.jwt.secret}")
    protected String SECRET_KEY;
    @Value("${application.security.jwt.expired-time}")
    protected Long EXPIRED_TIME;

    @Getter
    private Optional<UserDto> currentUser;


    public String generateToken(AuthDto authDto) {
        try {
            UserDto user = userService.findUserByLogin(authDto.getLogin());
            Date dateNow = Date.from(Instant.now());
            if (user.isActive() && user.getPassword().equals(passwordEncoderService.encodePassword(authDto.getPassword()))) {
                SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS384;
                byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
                Key key = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
                return Jwts.builder()
                        .setSubject(user.getLogin())
                        .setIssuedAt(dateNow)
                        .setExpiration(Date.from(dateNow.toInstant().plusSeconds(EXPIRED_TIME)))
                        .claim("roles", "user")
                        .signWith(key, signatureAlgorithm).compact();
            } else {
                passwordEncoderService.encodePassword("fake!237G58");
            }
        } catch (Exception exception) {
        }
        throw new UnauthorizedException();
    }

    public void authorize() {
        String authorization = request.getHeader("authorization");
        if (authorization != null && authorization.startsWith(BEARER_)) {
            authorization = authorization.substring(BEARER_.length());
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
            JwtParser jwtParser = Jwts.parserBuilder()
                    .setSigningKey(apiKeySecretBytes)
                    .build();
            try {
                Jwt jwt = jwtParser.parse(authorization);
                Claims claims = (Claims) jwt.getBody();
                String login = claims.get("sub").toString();
                currentUser = Optional.ofNullable(userService.findUserByLogin(login));
            } catch (Exception exception) {
                throw new UnauthorizedException();
            }
        } else {
            throw new UnauthorizedException();
        }
    }
}
