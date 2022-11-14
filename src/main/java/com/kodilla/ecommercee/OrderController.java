package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.entity.Order;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderService;
import com.kodilla.ecommercee.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final SecurityService securityService;
    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity <List<OrderDto>> getOrders() {
        List<Order> orders = orderService.getAllOrders();

        return ResponseEntity.ok(orderMapper.mapToOrderDtoList(orders));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) throws UserNotFoundException, CartNotFoundException {
        securityService.authorize();
        Order order = orderMapper.mapToOrder(orderDto);
        Order savedOrder = orderService.saveOrder(order);

        return ResponseEntity.ok(orderMapper.mapToOrderDto(savedOrder));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long id) throws OrderNotFoundException {
        Order order = orderService.getOrder(id);

        return ResponseEntity.ok(orderMapper.mapToOrderDto(order));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto) throws UserNotFoundException, CartNotFoundException{
        securityService.authorize();
        Order order = orderMapper.mapToOrder(orderDto);
        Order savedOrder = orderService.saveOrder(order);
        
        return ResponseEntity.ok(orderMapper.mapToOrderDto(savedOrder));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        securityService.authorize();
        orderService.deleteOrder(orderId);

        return ResponseEntity.ok().build();
    }
}
