package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.entities.User;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/v1/users")

public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }


    @PatchMapping(value = "/{userID}/blockUser")
    public ResponseEntity<Boolean> blockUser(@PathVariable long userID) {
       // User user = userRepository.findById(userID);
        return ResponseEntity.ok(true);



    }

    @PatchMapping(value = "/{userID}/generateKey")
    public UserDto generateToken(@PathVariable long userID) {

        String randomToken = UUID.randomUUID().toString();
        return UserDto.builder()
                .id(userID)
                .firstName("Test name")
                .surname("Test surname")
                .deliveryAddress("Test address")
                .login("Test login")
                .password("Test password")
                .key(randomToken)
                .isActive(true)
                .build();

    }

}
