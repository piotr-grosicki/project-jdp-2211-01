package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface OrderDao extends CrudRepository<Order,Long> {

    @Override
    Order save(Order order);

    Optional<Order> findById(long id);

    @Override
    List<Order> findAll();
}
