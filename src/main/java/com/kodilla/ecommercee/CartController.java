package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.entities.Product;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/carts")
@CrossOrigin("*")
public class CartController {

@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
void createNewCart(@RequestBody CartDto cartDto) {
}

@GetMapping(value ="/products")
List<Product> getProductsFromCart(@RequestParam long cartId) throws CartNotFoundException {
    return Arrays.asList(new Product(), new Product());
}

@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
CartDto addProductToCart(@RequestParam long cartId, @RequestParam long productId) throws CartNotFoundException, ProductNotFoundException{
    return CartDto.builder()
            .id(1)
            .userId(12)
            .listOfProducts(Arrays.asList(new Product()))
            .build();
}

@DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
CartDto removeProductFromCart(@RequestParam long cartId, @RequestParam long productId) throws CartNotFoundException, ProductNotFoundException {
    return CartDto.builder()
            .id(2)
            .userId(122)
            .listOfProducts(Arrays.asList(new Product()))
            .build();
}

@GetMapping("/order")
void createOrder(@RequestParam long cartId) throws CartNotFoundException{
}
}
