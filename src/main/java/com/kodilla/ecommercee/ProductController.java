package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<Product> productsList = productService.getListOfProducts();
        List<ProductDto> productDtoList = mapper.mapToProductsDtoList(productsList);

        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable long productId) throws ProductNotFoundException {
        Product product = productService.getProduct(productId);
        ProductDto productDto = mapper.mapToProductDto(product);

        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable long productId) throws ProductNotFoundException {
        productService.removeProduct(productId);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) throws ProductNotFoundException {
        Product product = mapper.mapToProduct(productDto);
        Product updatedProduct = productService.saveProduct(product);

        ProductDto savedProduct = mapper.mapToProductDto(updatedProduct);

        return ResponseEntity.ok(savedProduct);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Product product = mapper.mapToProduct(productDto);
        Product addedProduct = productService.saveProduct(product);

        ProductDto savedProduct = mapper.mapToProductDto(addedProduct);

        return ResponseEntity.ok(savedProduct);
    }
}
