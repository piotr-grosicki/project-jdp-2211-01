package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.entities.Product;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    return new ArrayList<>();
}

@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
CartDto addProductToCart(@RequestParam long cartId, @RequestParam long productId) throws CartNotFoundException, ProductNotFoundException{
    return new CartDto();
}

@DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
CartDto removeProductFromCart(@RequestParam long cartId, @RequestParam long productId) throws CartNotFoundException, ProductNotFoundException {
    return new CartDto();
}

@GetMapping("/order")
void createOrder(@RequestParam long cartId) throws CartNotFoundException{
}
}
