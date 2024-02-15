package com.heliosgems.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CategoryDto {
    private UUID uuid;

    private String name;

    private String description;

    private CategoryDto categoryParent;
}
