package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class GroupDto {
    private Long id;

    private String name;

    private String description;

    private List<Long> products;
}
