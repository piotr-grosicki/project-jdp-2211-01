package com.kodilla.ecommercee.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "Cart_id", unique = true)
    private long cartId;
}
