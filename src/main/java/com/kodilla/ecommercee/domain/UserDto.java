package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String firstName;
    private String surname;
    private String deliveryAddress;
    private String login;
    private String password;
    private boolean isActive;
}
