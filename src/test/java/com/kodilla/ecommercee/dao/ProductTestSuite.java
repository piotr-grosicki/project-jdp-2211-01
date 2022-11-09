package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.entity.*;
import com.kodilla.ecommercee.repository.CartDao;
import com.kodilla.ecommercee.repository.GroupDao;
import com.kodilla.ecommercee.repository.ProductDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

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
                .id(1L)
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

        /*User user = User.builder()
                .firstName("Test name")
                .surname("Test surname")
                .deliveryAddress("Test address")
                .login("Test login")
                .password("Test password")
                .build();

        Order order = Order.builder()
                .deliveryMethod("test")
                .deliveryAddress("test")
                .value(BigDecimal.valueOf(123))
                .orderDateTime(LocalDateTime.now())
                .user(user)
                .build();

        */

        Group group = new Group();

        Cart cart = new Cart();

        Product product = Product.builder()
                .name("Scissor")
                .description("Garden scissor")
                .price(BigDecimal.valueOf(555))
                .quantity(100)
                .carts(Arrays.asList(cart))
                .group(group)
                .build();

        cart.setListOfProducts(Arrays.asList(product));
        group.setProductsList(Arrays.asList(product));

        productDao.save(product);

        Optional<Product> productDb = productDao.findById(product.getId());
        Optional<Group> groupDb = groupDao.findById(group.getId());
        Optional<Cart> cartDb = cartDao.findByCartId(cart.getCartId());

        System.out.println(productDb.get());
        System.out.println(productDb.get().getDescription());

        assertTrue(productDb.isPresent());
        assertTrue(groupDb.isPresent());
        assertTrue(cartDb.isPresent());

        assertEquals(product.getId(), productDb.get().getId());
        assertEquals(cart.getCartId(), cartDb.get().getCartId());
        assertEquals(group.getId(), groupDb.get().getId());

       // productDao.deleteById(product.getId());
        //groupDao.deleteById(group.getId());
        //cartDao.deleteById(cart.getCartId());
    }

    @Test
    public void addingNewGroup() {
        User user = User.builder()
                .firstName("Test name")
                .surname("Test surname")
                .deliveryAddress("Test address")
                .login("Test login")
                .password("Test password")
                .build();

        Order order = Order.builder()
                .deliveryMethod("test")
                .deliveryAddress("test")
                .value(BigDecimal.valueOf(123))
                .orderDateTime(LocalDateTime.now())
                .build();

        Cart cart = Cart.builder()
                .cartId(1L)
                .user(user)
                .order(order)
                .build();

        Product product = Product.builder()
                .name("Scissor")
                .description("Garden scissor")
                .price(BigDecimal.valueOf(555))
                .quantity(100)
                .carts(Arrays.asList(cart))
                .build();

        Group group = Group.builder().build();

        //product.setGroup(group);
        //group.setProductsList(Arrays.asList(product));

        cart.setListOfProducts(Arrays.asList(product));

        Product savedProduct = productDao.save(product);

        Optional<Product> optionalProduct = productDao.findById(savedProduct.getId());

        assertTrue(optionalProduct.isPresent());

        productDao.deleteById(optionalProduct.get().getId());

       /*Group savedGroup = groupDao.save(group);

        System.out.println("Komentarz " + savedGroup.getId());

        Optional<Group> group1 = groupDao.findById(savedGroup.getId());

        System.out.println("Komentarz 2" + group1.get().getId());

        System.out.println(group1.get().getId() + " " + group.getId());

        groupDao.deleteById(group1.get().getId());

*/
    }
}


