package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartMapper {

    private final UserDao userDao;

    private final ProductMapper productMapper;

    public Cart mapToCart(CartDto cartDto) throws UserNotFoundException{
        return Cart.builder()
                .id(cartDto.getId())
                .user(userDao.findById(cartDto.getUserId()).orElseThrow(UserNotFoundException::new))
                .products(productMapper.mapToProductsList(cartDto.getProducts()))
                .build();
    }

    public CartDto mapToCartDto(Cart cart) {
        return CartDto.builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .products(productMapper.mapToProductsDtoList(cart.getProducts()))
                .build();
    }
}
