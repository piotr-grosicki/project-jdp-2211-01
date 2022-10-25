package com.kodilla.ecommercee;

import com.kodilla.ecommercee.entities.Cart;
import com.kodilla.ecommercee.entities.Order;
import com.kodilla.ecommercee.entities.User;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@SpringBootTest
//@DataJpaTest
@RunWith(SpringRunner.class)
public class UserTestSuite {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testSaveUser() {
        //Given
        User user = new User("Test name", "Test surname", "Test address", "Test login", "Test password");
        Order order = new Order();
        Cart cart = new Cart();
        //When

       // user.getOrderId().add(order);
        user.getCartId().add(cart);
        System.out.println(user);
        userRepository.save(user);


        //Then
        Long id = user.getId();
        Optional<User> readUser = userRepository.findById(id);
        System.out.println(readUser);
        assertTrue(readUser.isPresent());

        //CleanUp
        userRepository.deleteById(id);
    }
}
