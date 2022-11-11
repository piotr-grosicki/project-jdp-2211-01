package com.kodilla.ecommercee.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @JoinColumn(name = "USER_ID")
    private User user;
}
