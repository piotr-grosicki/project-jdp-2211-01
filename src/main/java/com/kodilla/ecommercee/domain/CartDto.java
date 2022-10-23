package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private long id;
    private long userId;
    private List<Product> listOfProducts;
}
