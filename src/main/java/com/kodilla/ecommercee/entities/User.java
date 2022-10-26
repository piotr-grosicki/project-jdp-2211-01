package com.kodilla.ecommercee.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "User_Id", unique = true)
    private long id;

    @Column(name = "Name")
    private String name;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Order> listOfOrder = new ArrayList<>();
}
