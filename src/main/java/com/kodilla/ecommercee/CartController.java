package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.ProductDto;

import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/carts")
@CrossOrigin("*")
public class CartController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    CartDto createNewCart(@RequestBody CartDto cartDto) {
        return CartDto.builder()
                .id(4L)
                .userId(341L)
                .build();
    }

    @GetMapping(value = "/{cartId}")
    List<ProductDto> getProductsFromCart(@PathVariable long cartId) throws CartNotFoundException {
        return Arrays.asList(
                ProductDto.builder()
                        .id(3L)
                        .name("Test product 1")
                        .build(),

                ProductDto.builder()
                        .id(33L)
                        .name("Test product 2")
                        .build());
    }

    @PutMapping
    CartDto addProductToCart(@RequestParam long cartId, @RequestParam long productId) throws CartNotFoundException, ProductNotFoundException {
        return CartDto.builder()
                .id(1)
                .userId(12)
                .listOfProducts(Arrays.asList(ProductDto.builder().id(3L).name("Test product").build()))
                .build();
    }

    @DeleteMapping
    CartDto removeProductFromCart(@RequestParam long cartId, @RequestParam long productId) throws CartNotFoundException, ProductNotFoundException {
        return CartDto.builder()
                .id(2)
                .userId(122)
                .listOfProducts(Arrays.asList(ProductDto.builder().id(3L).name("Test product").build()))
                .build();
    }

    @GetMapping(value = "/order")
    void createOrder(@RequestParam long cartId) throws CartNotFoundException {
    }
}
