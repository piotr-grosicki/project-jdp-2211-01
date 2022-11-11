package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .surname(userDto.getSurname())
                .deliveryAddress(userDto.getDeliveryAddress())
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .isActive(userDto.isActive())

                .build();

    }

    public UserDto mapToUserDto(final User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .surname(user.getSurname())
                .deliveryAddress(user.getDeliveryAddress())
                .login(user.getLogin())
                .password(user.getPassword())
                .isActive(user.isActive())
                .build();

    }
}
