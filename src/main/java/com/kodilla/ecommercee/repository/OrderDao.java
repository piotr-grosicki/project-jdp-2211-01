package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface OrderDao extends CrudRepository<Order, Long> {
    @Override
    List<Order> findAll();

    @Override
    Order save(Order order);

    @Override
    Optional<Order> findById(Long orderId);

    @Override
    void deleteById(Long orderId);
}
