package com.kodilla.ecommercee.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "USERS")
@Entity
public class User {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", unique = true)
    private long id;

    @Builder.Default
    @OneToMany(
            targetEntity = Cart.class,
            mappedBy = "user",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    private List<Cart> cartId = new ArrayList<>();

    @Builder.Default
    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "userId",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER)
    private List<Order> orderId = new ArrayList<>();

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

    @Column(name = "IS_ACTIVE")
    @Builder.Default
    private boolean isActive = true;


}
