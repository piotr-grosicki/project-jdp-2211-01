package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.entity.Group;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.repository.GroupDao;
import com.kodilla.ecommercee.repository.ProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GroupMapper {
    private final GroupDao groupDao;
    private final ProductDao productDao;

    public Group mapToEntity(final GroupDto groupDto) {
        Group group;
        if (groupDto.getId() != null) {
            group = groupDao.findById(groupDto.getId()).orElse(new Group());
        } else {
            group = new Group();
        }
        Group finalGroup = group;

        group.setId(groupDto.getId());
        group.setName(groupDto.getName());
        group.setDescription(groupDto.getDescription());
        List<Product> products = productDao.findByIdIn(groupDto.getProducts());
        group.setProducts(products);
        products.stream()
                .forEach(product -> product.setGroup(finalGroup));
        return group;
    }

    public GroupDto mapToDto(final Group group) {
        return GroupDto.builder()
                .id(group.getId())
                .name(group.getName())
                .description(group.getDescription())
                .products(group.getProducts().stream()
                        .map(Product::getId)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
