package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entities.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartDao extends CrudRepository<Cart,Long> {

    @Override
    Cart save(Cart cart);


    Optional<Cart> findById(Long id);
}
