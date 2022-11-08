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
import java.util.ArrayList;
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
        Cart cart1 = new Cart(user1, new ArrayList<>());
        Cart cart2 = new Cart(user2, new ArrayList<>());
        Order order1 = generateOrder();
        Order order2 = generateOrder();

        user1.getOrderId().add(order1);
        user1.getCartId().add(cart1);
        user2.getOrderId().add(order2);
        user2.getCartId().add(cart2);
        order1.setUser(user1);
        order1.setCartId(cart1);
        order2.setUser(user2);
        order2.setCartId(cart2);

        userDao.save(user1);
        userDao.save(user2);
        cartDao.save(cart1);
        cartDao.save(cart2);

        //When
        orderDao.save(order1);
        orderDao.save(order2);

        //Then
        assertEquals(2, orderDao.findAll().size());

        //Cleanup
        cartDao.deleteById(cart1.getCartId());
        cartDao.deleteById(cart2.getCartId());
        userDao.deleteById(user1.getId());
        userDao.deleteById(user2.getId());
        orderDao.deleteById(order1.getOrderId());
        orderDao.deleteById(order2.getOrderId());
    }

    @Test
    public void createOrder(){
        //Given
        User user = generateUser();
        Cart cart = new Cart(user, new ArrayList<>());
        Order order = generateOrder();

        user.getOrderId().add(order);
        user.getCartId().add(cart);
        order.setUser(user);
        order.setCartId(cart);

        userDao.save(user);
        cartDao.save(cart);

        //When
        orderDao.save(order);

        //Then
        assertEquals(1, orderDao.count());
        assertTrue(orderDao.findById(order.getOrderId()).isPresent());

        try {
            assertTrue(orderDao.findById(order.getOrderId()).isPresent());
        }
        catch(Exception e) {
            System.out.println("Order not created.");
        }

        //Cleanup
        cartDao.deleteById(cart.getCartId());
        userDao.deleteById(user.getId());
        orderDao.deleteById(order.getOrderId());
    }

    @Test
    public void getOrder(){
        //Given
        User user = generateUser();
        Cart cart = new Cart(user, new ArrayList<>());
        Order order = generateOrder();

        user.getOrderId().add(order);
        user.getCartId().add(cart);
        order.setUser(user);
        order.setCartId(cart);

        userDao.save(user);
        cartDao.save(cart);

        //When
        orderDao.save(order);

        //Then
        boolean result=false;
        try {
            result = orderDao.findById(order.getOrderId()).isPresent();
        }
        catch(Exception e) {
            System.out.println("Order not found.");
        }
        assertTrue(result);

        //Cleanup
        cartDao.deleteById(cart.getCartId());
        userDao.deleteById(user.getId());
        orderDao.deleteById(order.getOrderId());
    }

    @Test
    public void updateOrder(){
        //Given
        User user = generateUser();
        Cart cart = new Cart(user, new ArrayList<>());
        Order order = generateOrder();

        user.getOrderId().add(order);
        user.getCartId().add(cart);
        order.setUser(user);
        order.setCartId(cart);

        userDao.save(user);
        orderDao.save(order);
        cartDao.save(cart);

        //When
        Order updatedOrder = orderDao.findById(order.getOrderId()).get();
        updatedOrder.setValue(BigDecimal.valueOf(300.15));
        orderDao.save(updatedOrder);

        //Then
        assertEquals(BigDecimal.valueOf(300.15),orderDao.findById(updatedOrder.getOrderId()).get().getValue());

        //Cleanup
        cartDao.deleteById(cart.getCartId());
        userDao.deleteById(user.getId());
        orderDao.deleteById(order.getOrderId());
    }

    @Test
    public void deleteOrder(){
        //Given
        User user = generateUser();
        Cart cart = new Cart(user, new ArrayList<>());
        Order order = generateOrder();

        user.getOrderId().add(order);
        user.getCartId().add(cart);
        order.setUser(user);
        order.setCartId(cart);

        userDao.save(user);
        orderDao.save(order);
        cartDao.save(cart);

        //When
        orderDao.deleteById(order.getOrderId());

        //Then
        assertEquals(0, orderDao.findAll().size());
        assertTrue(userDao.existsById(user.getId()));
        assertTrue(cartDao.existsById(cart.getCartId()));

        //Cleanup
        cartDao.deleteById(cart.getCartId());
        userDao.deleteById(user.getId());
    }
}