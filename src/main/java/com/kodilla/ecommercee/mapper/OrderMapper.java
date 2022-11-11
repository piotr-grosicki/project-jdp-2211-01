package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {
    public Order mapToOrder(final OrderDto orderDto) {
        return Order.builder()
                .orderId(orderDto.getOrderId())
                .deliveryAddress(orderDto.getDeliveryAddress())
                .deliveryMethod(orderDto.getDeliveryMethod())
                .orderDateTime(orderDto.getOrderDateTime())
                .value(orderDto.getValue())
                .user(orderDto.getUser())
                .cartId(orderDto.getCartId())
                .build();
    }

    public OrderDto mapToOrderDto(final Order order) {
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .deliveryAddress(order.getDeliveryAddress())
                .deliveryMethod(order.getDeliveryMethod())
                .orderDateTime(order.getOrderDateTime())
                .value(order.getValue())
                .user(order.getUser())
                .cartId(order.getCartId())
                .build();
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orderList) {
        return orderList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}