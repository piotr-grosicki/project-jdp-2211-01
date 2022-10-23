package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    private int id;
    private String name;
    private String surname;
    private String deliveryAddress;
    private String login;
    private String password;
    private String key;
    private boolean isActive;
}
