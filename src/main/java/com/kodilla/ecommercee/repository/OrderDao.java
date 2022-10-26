package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface OrderDao extends CrudRepository<Order, Long> {
    Order save(Order order);
}
