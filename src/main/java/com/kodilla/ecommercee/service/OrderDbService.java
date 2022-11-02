package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.entities.Order;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.repository.OrderDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderDbService {
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======

>>>>>>> 15529a434b09cecacee300d80346118837d1fd5b
>>>>>>> f2d3c4ec2066c82f96428bd68ecd291e2b8b1760
    @Autowired
    private final OrderDao orderRepository;

    public List<Order> getALlOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(final Long orderId) throws OrderNotFoundException {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    public Order saveOrder(final Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(final Long orderId) throws OrderNotFoundException {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
        } else {
            throw new OrderNotFoundException();
        }
    }
}
