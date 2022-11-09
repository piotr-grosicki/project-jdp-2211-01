package com.kodilla.ecommercee.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "groupss")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @NotNull
    @Column
    private Long id;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "group",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private List<Product> productsList = new ArrayList<>();
}
