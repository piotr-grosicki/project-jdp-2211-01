package com.kodilla.ecommercee.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @Column(name="product_id", unique = true)
    private long id;
}
