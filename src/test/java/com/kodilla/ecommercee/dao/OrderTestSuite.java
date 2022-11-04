package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.entities.Cart;
import com.kodilla.ecommercee.entities.Order;
import com.kodilla.ecommercee.entities.User;
import com.kodilla.ecommercee.repository.OrderDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTestSuite {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void getOrders(){
        //Given
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();

        User user = User.builder()
                .firstName("John")
                .surname("Smith")
                .deliveryAddress("ul. Kasztanowa 14F/58 03-197 Warszawa")
                .login("john_smith")
                .password("john123456")
                .build();

        Order order1 = Order.builder()
                .deliveryMethod("courier")
                .deliveryAddress(user.getDeliveryAddress())
                .value(BigDecimal.valueOf(145))
                .orderDateTime(LocalDateTime.now())
                .cartId(cart1)
                .user(user)
                .build();

        Order order2 = Order.builder()
                .deliveryMethod("postal service")
                .deliveryAddress(user.getDeliveryAddress())
                .value(BigDecimal.valueOf(3285))
                .orderDateTime(LocalDateTime.now())
                .cartId(cart2)
                .user(user)
                .build();

        user.getCartId().add(cart1);
        user.getOrderId().add(order1);
        user.getCartId().add(cart2);
        user.getOrderId().add(order2);

        //When
        orderDao.save(order1);
        orderDao.save(order2);

        //Then
        assertEquals(2, orderDao.findAll().size());

        orderDao.deleteAll();
    }

    @Test
    public void createOrder(){
        //Given
        Cart cart = new Cart();

        User user = User.builder()
                .firstName("John")
                .surname("Smith")
                .deliveryAddress("ul. Kasztanowa 14F/58 03-197 Warszawa")
                .login("john_smith")
                .password("john123456")
                .build();

        Order order = Order.builder()
                .deliveryMethod("courier")
                .deliveryAddress(user.getDeliveryAddress())
                .value(BigDecimal.valueOf(145))
                .orderDateTime(LocalDateTime.now())
                .cartId(cart)
                .user(user)
                .build();

        user.getOrderId().add(order);
        user.getCartId().add(cart);

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
        Cart cart = new Cart();

        User user = User.builder()
                .firstName("John")
                .surname("Smith")
                .deliveryAddress("ul. Kasztanowa 14F/58 03-197 Warszawa")
                .login("john_smith")
                .password("john123456")
                .build();

        Order order = Order.builder()
                .deliveryMethod("courier")
                .deliveryAddress(user.getDeliveryAddress())
                .value(BigDecimal.valueOf(145))
                .orderDateTime(LocalDateTime.now())
                .cartId(cart)
                .user(user)
                .build();

        user.getOrderId().add(order);
        user.getCartId().add(cart);

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
        Cart cart = new Cart();

        User user = User.builder()
                .firstName("John")
                .surname("Smith")
                .deliveryAddress("ul. Kasztanowa 14F/58 03-197 Warszawa")
                .login("john_smith")
                .password("john123456")
                .build();

        Order order = Order.builder()
                .deliveryMethod("courier")
                .deliveryAddress(user.getDeliveryAddress())
                .value(BigDecimal.valueOf(145))
                .orderDateTime(LocalDateTime.now())
                .cartId(cart)
                .user(user)
                .build();

        user.getOrderId().add(order);
        user.getCartId().add(cart);

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
        Cart cart = new Cart();

        User user = User.builder()
                .firstName("John")
                .surname("Smith")
                .deliveryAddress("ul. Kasztanowa 14F/58 03-197 Warszawa")
                .login("john_smith")
                .password("john123456")
                .build();

        Order order = Order.builder()
                .deliveryMethod("courier")
                .deliveryAddress(user.getDeliveryAddress())
                .value(BigDecimal.valueOf(145))
                .orderDateTime(LocalDateTime.now())
                .cartId(cart)
                .user(user)
                .build();

        user.getOrderId().add(order);
        user.getCartId().add(cart);

        orderDao.save(order);

        //When
        orderDao.deleteById(order.getOrderId());

        //Then
        assertEquals(0, orderDao.findAll().size());
    }
}

