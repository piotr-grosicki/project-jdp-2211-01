package com.kodilla.ecommercee.domain;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@Builder
public class OrderDto {
    private long orderId;
    private String deliveryAddress;
    private String deliveryMethod;
    private LocalDateTime orderData;
    private BigDecimal value;
}
