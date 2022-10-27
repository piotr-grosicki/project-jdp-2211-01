package com.kodilla.ecommercee.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@AllArgsConstructor
@Table(name = "USERS")
@Entity
@Builder
public class User {

    @OneToMany(
            targetEntity = Cart.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)

    private List<Cart> cartId = new ArrayList<>();

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Order> orderId = new ArrayList<>();

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", unique = true)
    private long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "DELIVERY_ADDRESS")
    private String deliveryAddress;

    @Column(name = "LOGIN", unique = true)
    @NotNull
    private String login;

    @Column(name = "PASSWORD")
    @NotNull
    private String password;

    @Column(name = "KEY")
    private String key;

    @Column(name = "IS_ACTIVE")
    private boolean isActive;

  /*  public User(String firstName, String surname, String deliveryAddress, String login, String password) {
        this.firstName = firstName;
        this.surname = surname;
        this.deliveryAddress = deliveryAddress;
        this.login = login;
        this.password = password;
        this.key = "Test key";
        this.isActive = true;
    }*/
}
