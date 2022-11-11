package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Group;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.repository.CartDao;
import com.kodilla.ecommercee.repository.GroupDao;
import com.kodilla.ecommercee.repository.ProductDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductTestSuite {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private CartDao cartDao;

    @Test
    public void findProductByIdTest() {
        Product product = Product.builder()
                .name("Scissor")
                .description("Garden scissor")
                .price(BigDecimal.valueOf(555))
                .quantity(100)
                .build();

        productDao.save(product);

        Optional<Product> optionalProduct = productDao.findById(product.getId());

        assertTrue(optionalProduct.isPresent());

        Product savedProduct = optionalProduct.get();

        assertEquals(savedProduct.getId(), product.getId());

        productDao.deleteById(savedProduct.getId());
    }

    @Test
    public void addingNewProductWithGroup() {

        Group group = Group.builder()
                .name("Group test")
                .description("Description test")
                .build();

        Cart cart = new Cart();

        Product product = Product.builder()
                .name("Scissor")
                .description("Garden scissor")
                .price(BigDecimal.valueOf(555))
                .quantity(100)
                .carts(Arrays.asList(cart))
                .group(group)
                .build();

        productDao.save(product);

        Optional<Product> productDb = productDao.findById(product.getId());
        Optional<Group> groupDb = groupDao.findById(group.getId());
        Optional<Cart> cartDb = cartDao.findById(cart.getId());


        assertTrue(productDb.isPresent());
        assertTrue(groupDb.isPresent());
        assertTrue(cartDb.isPresent());

        assertEquals(product.getId(), productDb.get().getId());
        assertEquals(cart.getId(), cartDb.get().getId());
        assertEquals(group.getId(), groupDb.get().getId());

        productDao.deleteById(productDb.get().getId());
        groupDao.deleteById(groupDb.get().getId());
        cartDao.deleteById(cartDb.get().getId());
    }
}


