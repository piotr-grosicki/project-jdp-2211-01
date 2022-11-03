package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.entities.Cart;
import com.kodilla.ecommercee.entities.Order;
import com.kodilla.ecommercee.entities.User;
import com.kodilla.ecommercee.repository.CartDao;
import com.kodilla.ecommercee.repository.OrderDao;
import com.kodilla.ecommercee.repository.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTestSuite {

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CartDao cartDao;


    @Test
    public void testSaveUser() {

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

        Cart cart = new Cart();

        //When
        user.getOrderId().add(order);
        user.getCartId().add(cart);

        userDao.save(user);

        //Then
        Long id = user.getId();
        Optional<User> readUser = userDao.findById(id);

        Long orderId = order.getOrderId();
        Optional<Order> readOrder = orderDao.findById(orderId);

        Long cartId = cart.getCartId();
        Optional<Cart> readCart = cartDao.findByCartId(cartId);

        assertTrue(readUser.isPresent());

        //CleanUp
        userDao.deleteById(id);
        assertTrue(readOrder.isPresent());
        assertTrue(readCart.isPresent());
        orderDao.deleteById(orderId);
        cartDao.deleteById(cartId);
    }
}
