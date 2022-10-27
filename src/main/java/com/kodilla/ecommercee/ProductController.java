package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @GetMapping
    public List<ProductDto> getProducts() {
        return Arrays.asList(ProductDto.builder()
                        .id(1L)
                        .name("Product 1")
                        .description("Product description for tests")
                        .quantity(5)
                        .price(new BigDecimal(25L))
                        .build(),

                ProductDto.builder()
                        .id(2l)
                        .name("Product 2")
                        .description("Product 2 description for tests")
                        .quantity(4895)
                        .price(new BigDecimal(1555L))
                        .build());
    }

    @GetMapping(value = "/{productId}")
    public ProductDto getProduct(@PathVariable Long productId) throws ProductNotFoundException {
        return ProductDto.builder()
                .id(productId)
                .name("Product test")
                .description("Product description for tests - get product")
                .quantity(110)
                .price(new BigDecimal(55L))
                .build();
    }

    @DeleteMapping(value = "/{productId}")
    public ProductDto deleteProduct(@PathVariable Long productId) {
        return ProductDto.builder()
                .id(productId)
                .name("Product test")
                .description("Product description for tests - delete product")
                .quantity(110)
                .price(new BigDecimal(55L))
                .build();
    }

    @PutMapping
    public ProductDto updateTask(@RequestBody ProductDto productDto) throws ProductNotFoundException {
        return productDto;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return ProductDto.builder()
                .id(1L)
                .name("Product test")
                .description("Product description for tests - create product")
                .quantity(110)
                .price(new BigDecimal(55L))
                .build();
    }
}
