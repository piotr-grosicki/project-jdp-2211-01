package com.kodilla.ecommercee.domain;
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
    private String deliveryAdress;
    private String deliveryMethod;
    private LocalDateTime ordearDatatime;
    private BigDecimal value;
}
