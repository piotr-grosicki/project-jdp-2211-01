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
import java.util.ArrayList;
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

    @Test
    public void getOrders(){
        //Given
        User user = User.builder()
                .firstName("John")
                .surname("Smith")
                .deliveryAddress("ul. Kasztanowa 14F/58 03-197 Warszawa")
                .login("john_smith1")
                .password("john123456")
                .build();
        Cart cart1 = new Cart(user, new ArrayList<>());
        Cart cart2 = new Cart(user, new ArrayList<>());

        Order order1 = Order.builder()
                .deliveryMethod("courier")
                .deliveryAddress(user.getDeliveryAddress())
                .value(BigDecimal.valueOf(145))
                .orderDateTime(LocalDateTime.now())
                .build();

        Order order2 = Order.builder()
                .deliveryMethod("postal service")
                .deliveryAddress(user.getDeliveryAddress())
                .value(BigDecimal.valueOf(3285))
                .orderDateTime(LocalDateTime.now())
                .build();

        user.getCartId().add(cart1);
        user.getOrderId().add(order1);
        user.getCartId().add(cart2);
        user.getOrderId().add(order2);
        order1.setUser(user);
        order1.setCartId(cart1);
        order2.setUser(user);
        order2.setCartId(cart2);

        //When
        orderDao.save(order1);
        orderDao.save(order2);

        //Then
        assertEquals(2, orderDao.findAll().size());

        orderDao.deleteById(order1.getOrderId());
        orderDao.deleteById(order2.getOrderId());
    }

    @Test
    public void createOrder(){
        //Given
        User user = User.builder()
                .firstName("John")
                .surname("Smith")
                .deliveryAddress("ul. Kasztanowa 14F/58 03-197 Warszawa")
                .login("john_smith2")
                .password("john123456")
                .build();
        Cart cart = new Cart(user, new ArrayList<>());

        Order order = Order.builder()
                .deliveryMethod("courier")
                .deliveryAddress(user.getDeliveryAddress())
                .value(BigDecimal.valueOf(145))
                .orderDateTime(LocalDateTime.now())
                .build();

        user.getOrderId().add(order);
        user.getCartId().add(cart);
        order.setUser(user);
        order.setCartId(cart);

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
        orderDao.deleteById(order.getOrderId());
    }

    @Test
    public void getOrder(){
        //Given
        User user = User.builder()
                .firstName("John")
                .surname("Smith")
                .deliveryAddress("ul. Kasztanowa 14F/58 03-197 Warszawa")
                .login("john_smith3")
                .password("john123456")
                .build();
        Cart cart5 = new Cart(user, new ArrayList<>());

        Order order = Order.builder()
                .deliveryMethod("courier")
                .deliveryAddress(user.getDeliveryAddress())
                .value(BigDecimal.valueOf(145))
                .orderDateTime(LocalDateTime.now())
                .build();

        user.getOrderId().add(order);
        user.getCartId().add(cart5);
        order.setUser(user);
        order.setCartId(cart5);

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

        orderDao.deleteById(order.getOrderId());
    }

    @Test
    public void updateOrder(){
        //Given
        User user = User.builder()
                .firstName("John")
                .surname("Smith")
                .deliveryAddress("ul. Kasztanowa 14F/58 03-197 Warszawa")
                .login("john_smith4")
                .password("john123456")
                .build();
        Cart cart4 = new Cart(user, new ArrayList<>());

        Order order = Order.builder()
                .deliveryMethod("courier")
                .deliveryAddress(user.getDeliveryAddress())
                .value(BigDecimal.valueOf(145))
                .orderDateTime(LocalDateTime.now())
                .build();

        user.getOrderId().add(order);
        user.getCartId().add(cart4);
        order.setUser(user);
        order.setCartId(cart4);

        orderDao.save(order);

        //When
        Order updatedOrder = orderDao.findById(order.getOrderId()).get();
        updatedOrder.setValue(BigDecimal.valueOf(300.15));
        orderDao.save(updatedOrder);

        //Then
        assertEquals(BigDecimal.valueOf(300.15),orderDao.findById(updatedOrder.getOrderId()).get().getValue());

        orderDao.deleteById(order.getOrderId());
    }

    @Test
    public void deleteOrder(){
        //Given
        User user = User.builder()
                .firstName("John")
                .surname("Smith")
                .deliveryAddress("ul. Kasztanowa 14F/58 03-197 Warszawa")
                .login("john_smith5")
                .password("john123456")
                .build();
        Cart cart6 = new Cart(user, new ArrayList<>());
        Order order = Order.builder()
                .deliveryMethod("courier")
                .deliveryAddress(user.getDeliveryAddress())
                .value(BigDecimal.valueOf(145))
                .orderDateTime(LocalDateTime.now())
                .build();

        user.getOrderId().add(order);
        user.getCartId().add(cart6);
        order.setUser(user);
        order.setCartId(cart6);

        orderDao.save(order);

        //When
        orderDao.deleteById(order.getOrderId());

        //Then
        assertEquals(0, orderDao.findAll().size());
    }
}