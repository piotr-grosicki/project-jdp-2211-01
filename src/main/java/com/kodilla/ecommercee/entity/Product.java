package com.kodilla.ecommercee.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NonNull
    @Column(length = 100)
    @Length(min = 2, max = 100)
    private String name;

    @NonNull
    @Column(length = 1000)
    @Length(min = 2, max = 1000)
    private String description;

    @NonNull
    @PositiveOrZero
    private Integer quantity;

    @NonNull
    @Positive
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToMany(
            mappedBy = "products",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    private List<Cart> carts;
}
