package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductDao extends CrudRepository<Product, Long> {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    @Override
    Product save(Product product);
}


