package com.kodilla.ecommercee.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Group")
public class Group {
    @Id
    @GeneratedValue
    @Column(name="Id", unique=true)
    private long id;

    @NotNull
    @Column(name="Name")
    private String name;

    @NotNull
    @Column(name="Description")
    private String description;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "group",
            fetch = FetchType.EAGER
    )
    private List<Product> productList;


}
