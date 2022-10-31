package com.kodilla.ecommercee.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name= "carts")
public class Cart {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="cart_id", unique = true)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id" )
    private User user;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "product_cart",
            joinColumns = {@JoinColumn(name = "cart_id", referencedColumnName = "cart_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "product_id")}
    )
    private List<Product> listOfProducts;

    public Cart(User user, List<Product> listOfProducts) {
        this.user = user;
        this.listOfProducts = listOfProducts;
    }

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "ORDER_ID")
    private Order order;
}
