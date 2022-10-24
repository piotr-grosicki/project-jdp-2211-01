package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
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
            mappedBy = "group_id",
            fetch = FetchType.EAGER
    )
    private List<Product> productList;


}
