package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/carts")
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

    @PutMapping(value = "/{id}/{productId}")
    CartDto addProductToCart(@PathVariable long id, @PathVariable long productId) throws CartNotFoundException, ProductNotFoundException {
        return CartDto.builder()
                .id(id)
                .userId(12L)
                .listOfProducts(Arrays.asList(ProductDto.builder().id(productId).name("Test product").build()))
                .build();
    }

    @DeleteMapping(value = "/{id}/{productId}")
    CartDto removeProductFromCart(@PathVariable long id, @PathVariable long productId) throws CartNotFoundException, ProductNotFoundException {
        return CartDto.builder()
                .id(id)
                .userId(122L)
                .listOfProducts(Arrays.asList(ProductDto.builder().id(3L).name("Test product").build()))
                .build();
    }

    @GetMapping(value = "/{id}/order")
    void createOrder(@RequestParam long id) throws CartNotFoundException {
    }
}
