package com.kodilla.ecommercee.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    @Builder.Default
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    @Builder.Default
    private List<Order> orders = new LinkedList<>();

    private String firstName;

    private String surname;

    private String deliveryAddress;

    @NotNull
    @Column(unique = true)
    private String login;

    @NotNull
    private String password;

    @Builder.Default
    private boolean isActive = true;
}
