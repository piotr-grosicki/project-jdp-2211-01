package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.entities.Order;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderDbService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@Builder
@RequiredArgsConstructor
public class OrderController {
    private final OrderMapper orderMapper;
    private final OrderDbService orderDbService;

    @GetMapping
    public List<OrderDto> getOrders() {
        List<Order> orders = orderDbService.getALlOrders();
        return orderMapper.mapToOrderDtoList(orders);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createOrder(@RequestBody OrderDto orderDto) {
        Order order = orderMapper.mapToOrder(orderDto);
        orderDbService.saveOrder(order);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        return ResponseEntity.ok(orderMapper.mapToOrderDto(orderDbService.getOrder(orderId)));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto) {
        Order order = orderMapper.mapToOrder(orderDto);
        Order saveOrder = orderDbService.saveOrder(order);
        return ResponseEntity.ok(orderMapper.mapToOrderDto(saveOrder));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        orderDbService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
