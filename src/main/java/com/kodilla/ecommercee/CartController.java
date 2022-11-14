package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartMapper cartMapper;
    private final ProductMapper productMapper;
    private final CartService cartService;

    private final ProductService productService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CartDto> createNewCart(@RequestBody CartDto cartDto) throws UserNotFoundException {
        Cart cart = cartService.saveCart(cartMapper.mapToCart(cartDto));
        CartDto savedCart = cartMapper.mapToCartDto(cart);

        return ResponseEntity.ok(savedCart);
    }

    @GetMapping(value = "/{cartId}")
    ResponseEntity<List<ProductDto>> getProductsFromCart(@PathVariable long cartId) throws CartNotFoundException {
        List<Product> products = cartService.getListOfProduct(cartId);
        List<ProductDto> productsDto = productMapper.mapToProductsDtoList(products);

        return ResponseEntity.ok(productsDto);
    }

    @PutMapping(value = "/{id}/{productId}")
    ResponseEntity<CartDto> addProductToCart(@PathVariable long id, @PathVariable long productId) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartService.getCartById(id);
        Product product = productService.getProduct(productId);

        cart.getProducts().add(product);

        Cart savedCart = cartService.saveCart(cart);

        CartDto responseCartDto = cartMapper.mapToCartDto(savedCart);

        return ResponseEntity.ok(responseCartDto);
    }

    @DeleteMapping(value = "/{id}/{productId}")
    ResponseEntity<CartDto> removeProductFromCart(@PathVariable long id, @PathVariable long productId) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartService.getCartById(id);
        Product product = productService.getProduct(productId);

        cart.getProducts().remove(product);
        cartService.saveCart(cart);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/order",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    void createOrder(@RequestParam long id, @RequestBody OrderDto orderDto) throws CartNotFoundException {
    }
}
