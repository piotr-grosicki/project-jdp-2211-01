package com.kodilla.ecommercee.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "`group`")
@Table(name = "`group`")
public class Group {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "group",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private List<Product> products;


}