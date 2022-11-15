package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartDao extends CrudRepository<Cart, Long> {

    @Override
    Cart save(Cart cart);

    @Override
    List<Cart> findAll();

    @Override
    Optional<Cart> findById(Long id);
}
