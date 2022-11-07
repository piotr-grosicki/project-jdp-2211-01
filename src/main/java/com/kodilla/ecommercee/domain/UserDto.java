package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class UserDto {

    private long id;
    private String firstName;
    private String surname;
    private String deliveryAddress;
    private String login;
    private String password;
    private boolean isActive;
}
