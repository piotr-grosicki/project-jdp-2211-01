package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartMapper {
    private final UserDao userDao;
    private final ProductMapper productMapper;

    public Cart mapToCart(final CartDto cartDto) throws UserNotFoundException {
        return Cart.builder()
                .id(cartDto.getId())
                .user(userDao.findById(cartDto.getId()).orElseThrow(UserNotFoundException::new))
                .products(productMapper.mapToProductsList(cartDto.getListOfProducts()))
                .build();

    }

    public CartDto mapToCartDto(final Cart cart) {
        return CartDto.builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .listOfProducts(productMapper.mapToProductsDtoList(cart.getProducts()))
                .build();
    }

    public List<CartDto> mapToCartDtoList(final List<Cart> cartList) {
        return cartList.stream().map(this::mapToCartDto).collect(Collectors.toList());
    }
}
