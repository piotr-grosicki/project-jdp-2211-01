package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Group;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.repository.GroupDao;
import com.kodilla.ecommercee.repository.ProductDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GroupTestSuite {
    @Autowired
    private GroupDao groupDao;

    @Autowired
    private ProductDao productDao;

    @Test
    public void testAddGroup() {
        //given
        Group group = Group.builder()
                .name("PC")
                .description("Personal Computer")
                .build();

        //when
        groupDao.save(group);
        Long groupId = group.getId();

        //then
        assertNotNull(groupId);
        //clean
        groupDao.deleteById(groupId);
    }

    @Test
    public void testGetGroupById() {
        //given
        Group group = Group.builder()
                .name("PC")
                .description("Personal Computer")
                .build();

        //when
        groupDao.save(group);
        Long groupId = group.getId();
        Optional<Group> groupTest = groupDao.findById(groupId);
        //then
        assertEquals(groupId, groupTest.get().getId());
        //clean
        groupDao.deleteById(groupId);

    }

    @Test
    public void testAddGroupToProduct() {
        //given
        Group group = Group.builder()
                .name("Group test")
                .description("Description test")
                .build();

        Cart cart = new Cart();

        Product product = Product.builder()
                .name("Product test")
                .description("Description test")
                .price(BigDecimal.valueOf(555))
                .quantity(100)
                .carts(Arrays.asList(cart))
                .group(group)
                .build();
        //when
        productDao.save(product);
        groupDao.save(group);
        Optional<Product> productResult = productDao.findById(product.getId());
        Optional<Group> groupResult = groupDao.findById(group.getId());

        //then
        assertEquals(product.getGroup().getId(), group.getId());

        //clean

        productDao.deleteById(productResult.get().getId());
        groupDao.deleteById(groupResult.get().getId());
    }

}
