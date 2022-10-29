package com.kodilla.ecommercee.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "Id", unique = true)
    private long orderId;

    @Column(name = "Delivery_method")
    @NotNull
    private String deliveryMethod;

    @Column(name = "Delivery_address")
    @NotNull
    private String deliveryAddress;

    @Column(name = "Value")
    @NotNull
    private BigDecimal value;

    @Column(name = "Order_Date_Time")
    @NotNull
    private LocalDateTime orderDateTime;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "Card_Id")
    private Cart cartId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "User_Id")
    private User user;
}
