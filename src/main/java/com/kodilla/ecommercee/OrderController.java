package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    @GetMapping
    public List<OrderDto> getOrders() {
        return Arrays.asList(OrderDto.builder()
                .orderId(1)
                .deliveryAdress("ul.Warszawska 21, 02-550 Warszawa")
                .deliveryMethod("Courier GLS")
                .ordearDatatime(LocalDateTime.now())
                .value(new BigDecimal("2000.11 PLN"))
                .build(),

        OrderDto.builder()
                .orderId(2)
                .deliveryAdress("ul.Wrocławska 22, 03-220 Wrocław")
                .deliveryMethod("Courier UPS")
                .ordearDatatime(LocalDateTime.now())
                .value(new BigDecimal("1223.00 PLN"))
                .build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return OrderDto.builder()
                .orderId(3)
                .deliveryAdress("ul.Poznańska 22, 03-220 Poznań")
                .deliveryMethod("Courier UPS")
                .ordearDatatime(LocalDateTime.now())
                .value(new BigDecimal("1223.00 PLN"))
                .build();
    }

    @GetMapping(value = "/{orderId}")
    public OrderDto getOrder(@PathVariable int orderId) throws OrderNotFoundException {
        return OrderDto.builder()
                .orderId(orderId)
                .deliveryAdress("Warszawska 21, 02-550 Warszawa")
                .deliveryMethod("Courier GLS")
                .ordearDatatime(LocalDateTime.now())
                .value(new BigDecimal("2000.11PLN"))
                .build();

    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) throws OrderNotFoundException {
        return orderDto;
    }

    @DeleteMapping("/{orderId}")
    public OrderDto deleteOrder(@PathVariable int taskId) {
        return deleteOrder(taskId);
    }


}
