package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartDao extends CrudRepository<Cart, Long> {

    @Override
    Cart save(Cart cart);


    Optional<Cart> findByCartId(long id);
}
