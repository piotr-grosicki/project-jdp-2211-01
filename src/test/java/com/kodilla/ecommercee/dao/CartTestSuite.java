package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.entity.*;
import com.kodilla.ecommercee.repository.CartDao;
import com.kodilla.ecommercee.repository.OrderDao;
import com.kodilla.ecommercee.repository.ProductDao;
import com.kodilla.ecommercee.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration
@RequiredArgsConstructor
@WebAppConfiguration
public class CartTestSuite {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;

    @Test
    @Transactional
    public void testSaveCart() {
        //Given
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

        Group group = Group.builder()
                .name("test")
                .description("test2")
                .build();

        Product product = new Product();
        product.setName("Name");
        product.setDescription("Description");
        product.setPrice(BigDecimal.valueOf(50));
        product.setQuantity(10);
        product.setGroup(group);

        Product product2 = new Product();
        product.setName("Name2");
        product.setDescription("Description");
        product.setPrice(BigDecimal.valueOf(50));
        product.setQuantity(10);
        product.setGroup(group);

        List<Product> products = Lists.newArrayList(product, product, product2);

        //When
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setOrder(order);
        cart.setProducts(products);

        cartDao.save(cart);

        //Then
        Long cartId = cart.getId();
        Optional<Cart> readCart = cartDao.findById(cartId);

        Long id = user.getId();
        Optional<User> readUser = userDao.findById(id);

        Long orderId = order.getId();
        Optional<Order> readOrder = orderDao.findById(orderId);

        Long productId = product.getId();
        Optional<Product> readProduct = productDao.findById(productId);

        Long product2Id = product2.getId();
        Optional<Product> readProduct2 = productDao.findById(product2Id);

        assertTrue(readCart.isPresent());
        List<Product> readProducts = readCart.get().getProducts();
        assertEquals(3, readProducts.size());
        cartDao.deleteById(cartId);
        assertTrue(readUser.isPresent());
        assertTrue(readOrder.isPresent());
        assertTrue(readProduct.isPresent());
        assertTrue(readProduct2.isPresent());

        //CleanUp
        userDao.deleteById(id);
        orderDao.deleteById(orderId);
        productDao.deleteById(productId);
        productDao.deleteById(product2Id);
    }
}
