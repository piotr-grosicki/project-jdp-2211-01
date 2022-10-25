package com.kodilla.ecommercee;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class EcommerceeApplicationTests {

    @Autowired
    OrderDao orderDao;
    @Test
    public void contextLoads() {
    }

    @Test
    public void addingNewOrder() {

        BigDecimal bigDecimal = BigDecimal.valueOf(100L);

        LocalDateTime time = LocalDateTime.of(2121, 1,1, 1, 1);
        Order order = new Order(21L, "m1", "d1", bigDecimal, time, new Cart(), new User());


        orderDao.save(order);
    }

}

