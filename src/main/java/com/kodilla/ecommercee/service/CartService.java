package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.repository.CartDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartDao cartDao;

    public Cart getCartById(final Long cartId) throws CartNotFoundException {
        return cartDao.findById(cartId).orElseThrow(CartNotFoundException::new);
    }

    public Cart saveCart(final Cart cart) {
        return cartDao.save(cart);
    }

    public List<Cart> getAllCarts() {
        return cartDao.findAll();
    }

    public List<Product> getListOfProduct(long id) throws CartNotFoundException {
        Cart cart = cartDao.findById(id).orElseThrow(CartNotFoundException::new);
        return cart.getProducts();
    }

    public void deleteCart(final Long cartId) throws CartNotFoundException {
        try {
            cartDao.deleteById(cartId);
        } catch (Exception e) {
            throw new CartNotFoundException();
        }
    }
}
