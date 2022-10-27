package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.entities.Cart;
import com.kodilla.ecommercee.entities.Order;
import com.kodilla.ecommercee.entities.User;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTestSuite {

    @Autowired
    private UserRepository userRepository;



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
        Order order = new Order();
        Cart cart = new Cart();
        //When

        user.getOrderId().add(order);
        user.getCartId().add(cart);

        userRepository.save(user);


        //Then
        Long id = user.getId();
        Optional<User> readUser = userRepository.findById(id);
        //Long orderId = order.getOrderId();
     //   Optional<Order> readOrder = orderDao.findById(orderId);

        //CleanUp
        userRepository.deleteById(id);
        //assertTrue(readOrder.isPresent());

    }
}
