package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/v1/users")


public class UserController {


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userDto;
    }

    @PatchMapping(value = "/{userID/blockUser")
    public boolean blockUser(@PathVariable int userID, boolean isActive) {
        return true;

    }

    @PatchMapping(value = "/{userID}/generateKey")
    public UserDto generateToken(@PathVariable int userID) {

        String randomToken = UUID.randomUUID().toString();
        return UserDto.builder()
                .id(userID)
                .name("Test name")
                .surname("Test surname")
                .deliveryAddress("Test address")
                .login("Test login")
                .password("Test password")
                .key(randomToken)
                .isActive(true)
                .build();

    }

}
