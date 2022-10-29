package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entities.Order;
import com.kodilla.ecommercee.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderDao extends CrudRepository<Order,Long> {

    @Override
    Order save(Order order);

    Optional<Order> findById(long id);
}
