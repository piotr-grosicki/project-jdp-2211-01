package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.repository.CartDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartDao cartDao;

    public Cart saveCart(Cart cart) {
        return cartDao.save(cart);
    }

    public List<Product> getListOfProduct(long id) throws CartNotFoundException {
        Cart cart = cartDao.findById(id).orElseThrow(CartNotFoundException::new);
        return cart.getProducts();
    }

    public Cart getCartById(long id) throws CartNotFoundException {
        return cartDao.findById(id).orElseThrow(CartNotFoundException::new);
    }
}
