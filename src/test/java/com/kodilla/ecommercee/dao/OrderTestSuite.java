package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Order;
import com.kodilla.ecommercee.entity.User;
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
import java.util.Random;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTestSuite {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CartDao cartDao;

    private User generateUser(){
        Random random = new Random();
        int number = random.nextInt();
        return User.builder()
                .firstName("John")
                .surname("Smith")
                .deliveryAddress("ul. Kasztanowa 14F/58 03-197 Warszawa")
                .login("" + number)
                .password("john123456")
                .build();
    }

    private Order generateOrder(){
        return Order.builder()
                .deliveryMethod("courier")
                .deliveryAddress("delivery address")
                .value(BigDecimal.valueOf(145))
                .orderDateTime(LocalDateTime.now())
                .build();
    }

    @Test
    public void getOrders(){
        //Given
        User user1 = generateUser();
        User user2 = generateUser();
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Order order1 = generateOrder();
        Order order2 = generateOrder();

        order1.setUserId(user1);
        order1.setCartId(cart1);
        order2.setUserId(user2);
        order2.setCartId(cart2);

        //When
        orderDao.save(order1);
        orderDao.save(order2);

        //Then
        assertEquals(2, orderDao.findAll().size());

        //Cleanup
        userDao.deleteById(user1.getId());
        userDao.deleteById(user2.getId());
        orderDao.deleteById(order1.getOrderId());
        orderDao.deleteById(order2.getOrderId());
        cartDao.deleteById(cart1.getCartId());
        cartDao.deleteById(cart2.getCartId());
    }

    @Test
    public void createOrder(){
        //Given
        User user = generateUser();
        Cart cart = new Cart();
        Order order = generateOrder();

        order.setUserId(user);
        order.setCartId(cart);

        //When
        orderDao.save(order);

        //Then
        assertEquals(1, orderDao.count());
        assertTrue(orderDao.findById(order.getOrderId()).isPresent());

        //Cleanup
        userDao.deleteById(user.getId());
        orderDao.deleteById(order.getOrderId());
        cartDao.deleteById(cart.getCartId());
    }

    @Test
    public void getOrder(){
        //Given
        User user = generateUser();
        Cart cart = new Cart();
        Order order = generateOrder();

        order.setUserId(user);
        order.setCartId(cart);

        //When
        orderDao.save(order);

        //Then
        boolean result=false;
        result = orderDao.findById(order.getOrderId()).isPresent();
        assertTrue(result);

        //Cleanup
        userDao.deleteById(user.getId());
        orderDao.deleteById(order.getOrderId());
        cartDao.deleteById(cart.getCartId());
    }

    @Test
    public void updateOrder(){
        //Given
        User user = generateUser();
        Cart cart = new Cart();
        Order order = generateOrder();

        order.setUserId(user);
        order.setCartId(cart);
        orderDao.save(order);

        //When
        order.setValue(BigDecimal.valueOf(300.15));
        orderDao.save(order);

        //Then
        Optional<Order> updatedOrder = orderDao.findById(order.getOrderId());
        assertTrue(updatedOrder.isPresent());
        assertEquals(BigDecimal.valueOf(300.15),updatedOrder.get().getValue());

        //Cleanup
        userDao.deleteById(user.getId());
        orderDao.deleteById(order.getOrderId());
        cartDao.deleteById(cart.getCartId());
    }

    @Test
    public void deleteOrder(){
        //Given
        User user = generateUser();
        Cart cart = new Cart();
        Order order = generateOrder();

        order.setUserId(user);
        order.setCartId(cart);
        orderDao.save(order);

        //When
        orderDao.deleteById(order.getOrderId());

        //Then
        assertEquals(0, orderDao.findAll().size());
        assertTrue(userDao.existsById(user.getId()));
        assertTrue(cartDao.existsById(cart.getCartId()));

        //Cleanup
        userDao.deleteById(user.getId());
        cartDao.deleteById(cart.getCartId());
    }
}