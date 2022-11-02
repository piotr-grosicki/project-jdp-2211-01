package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entities.Order;
import org.springframework.data.repository.CrudRepository;

<<<<<<< HEAD
import java.util.List;
import java.util.Optional;

public interface OrderDao extends CrudRepository<Order,Long> {

=======
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface OrderDao extends CrudRepository<Order, Long> {
>>>>>>> f2d3c4ec2066c82f96428bd68ecd291e2b8b1760
    @Override
    List<Order> findAll();

    @Override
    Order save(Order order);

    @Override
    Optional<Order> findById(Long orderId);

    @Override
    void deleteById(Long orderId);
}
