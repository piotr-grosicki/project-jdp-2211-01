package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.repository.ProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;

    public List<Product> getListOfProducts() {
        return productDao.findAll();
    }

    public Product getProduct(Long id) throws ProductNotFoundException {
        return productDao.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public Product saveProduct(Product product) {
        return productDao.save(product);
    }

    public void removeProduct(Long id) throws ProductNotFoundException {
        Optional<Product> product = productDao.findById(id);
        productDao.delete(product.orElseThrow(ProductNotFoundException::new));
    }
}
