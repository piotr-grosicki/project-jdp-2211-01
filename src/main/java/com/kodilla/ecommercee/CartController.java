package com.kodilla.ecommercee;

import com.kodilla.ecommercee.annotation.AuthorizeBeforeModifying;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Order;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.OrderDbService;
import com.kodilla.ecommercee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/v1/carts")
@AuthorizeBeforeModifying
@RequiredArgsConstructor
public class CartController {

    private final CartMapper cartMapper;
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;
    private final CartService cartService;
    private final ProductService productService;
    private final OrderDbService orderDbService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartDto> createNewCart(@RequestBody CartDto cartDto) throws UserNotFoundException {
        Cart cart = cartService.saveCart(cartMapper.mapToCart(cartDto));
        CartDto savedCart = cartMapper.mapToCartDto(cart);

        return ResponseEntity.ok((savedCart));
    }

    @GetMapping(value = "/{cartId}")
    public ResponseEntity<List<ProductDto>> getProductsFromCart(@PathVariable Long cartId) throws CartNotFoundException {
        List<Product> products = cartService.getListOfProduct(cartId);
        List<ProductDto> productDto = productMapper.mapToProductsDtoList(products);
        return ResponseEntity.ok(productDto);
    }

    @PutMapping(value = "/{id}/{productId}")
    public ResponseEntity<CartDto> addProductToCart(@PathVariable Long id, @PathVariable Long productId) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartService.getCartById(id);
        Product product = productService.getProduct(productId);
        cart.getProducts().add(product);
        Cart savedCart = cartService.saveCart(cart);
        CartDto responseCartDto = cartMapper.mapToCartDto(savedCart);
        return ResponseEntity.ok(responseCartDto);
    }

    @DeleteMapping(value = "/{id}/{productId}")
    public ResponseEntity<CartDto> removeProductFromCart(@PathVariable Long id, @PathVariable Long productId) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartService.getCartById(id);
        Product product = productService.getProduct(productId);
        cart.getProducts().remove(product);
        cartService.saveCart(cart);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{id}/order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> createOrder(@RequestParam Long id, @RequestBody OrderDto orderDto) throws CartNotFoundException, UserNotFoundException {
        Order order = orderMapper.mapToOrder(orderDto);
        Cart cart = cartService.getCartById(id);
        order.setCart(cart);
        Order savedOrder = orderDbService.saveOrder(order);
        return ResponseEntity.ok(orderMapper.mapToOrderDto(savedOrder));
    }
}
