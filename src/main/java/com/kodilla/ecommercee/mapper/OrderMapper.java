package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.entity.Order;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderMapper {

    private final UserService userService;
    public Order mapToOrder(final OrderDto orderDto) throws UserNotFoundException {
        return Order.builder()
                .id(orderDto.getId())
                .deliveryAddress(orderDto.getDeliveryAddress())
                .deliveryMethod(orderDto.getDeliveryMethod())
                .orderDateTime(orderDto.getOrderDateTime())
                .value(orderDto.getValue())
                .user(userService.findUser(orderDto.getUserId()))
                .cart(null)
                .build();
    }

    public OrderDto mapToOrderDto(final Order order) {
        return OrderDto.builder()
                .Id(order.getId())
                .deliveryAddress(order.getDeliveryAddress())
                .deliveryMethod(order.getDeliveryMethod())
                .orderDateTime(order.getOrderDateTime())
                .value(order.getValue())
                .userId(order.getUser().getId())
                .cartId(order.getCart().getId())
                .build();
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orderList) {
        return orderList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}
