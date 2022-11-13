package com.kodilla.ecommercee;


import com.kodilla.ecommercee.domain.AuthDto;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.service.SecurityService;
import com.kodilla.ecommercee.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


@AllArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) throws NoSuchAlgorithmException, IOException {
        userService.createUser(userDto);
        return ResponseEntity.ok(null);
    }


    @PatchMapping(value = "/{userID}/blockUser")
    public ResponseEntity<Void> blockUser(@PathVariable long userID) throws UserNotFoundException {
        securityService.authorize();
        userService.blockUser(userID);
        return ResponseEntity.ok(null);
    }


    @PostMapping(value = "/generateToken")
    public ResponseEntity<String> generateToken(@RequestBody AuthDto authDto) throws NoSuchAlgorithmException, IOException {
        return ResponseEntity.ok(securityService.generateToken(authDto));
    }
}
