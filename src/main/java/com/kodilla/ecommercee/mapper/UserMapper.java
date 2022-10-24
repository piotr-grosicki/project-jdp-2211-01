package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {


    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getDeliveryAddress(),
                userDto.getLogin(),
                userDto.getPassword(),
                userDto.getKey(),
                userDto.isActive()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getDeliveryAddress(),
                user.getLogin(),
                user.getPassword(),
                user.getKey(),
                user.isActive()
        );
    }
}
