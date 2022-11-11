package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@Builder
public class OrderDto {
    private long orderId;
    private String deliveryAddress;
    private String deliveryMethod;
    private LocalDateTime orderDateTime;
    private BigDecimal value;
    private Cart cartId;
    private User user;
}
