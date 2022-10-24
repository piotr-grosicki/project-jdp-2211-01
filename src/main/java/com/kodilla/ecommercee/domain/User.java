package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name ="ID",unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name="SURNAME")
    private String surname;
    @Column(name="DELIVERY_ADDRESS")
    private String deliveryAddress;
    @Column(name = "LOGIN", nullable = false, unique = true)
    private String login;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name="KEY")
    private String key;
    @Column(name="IS_ACTIVE")
    private boolean isActive;
}
