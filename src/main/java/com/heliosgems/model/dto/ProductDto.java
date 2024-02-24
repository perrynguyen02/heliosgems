package com.heliosgems.model.dto;

import com.heliosgems.model.entity.Category;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
public class ProductDto {
    private UUID uuid;
    private String name;
    private String description;

    private Long price;
    private Long quantity;
    private String unit;

    private Category category;

    private byte[] byteImg;
    private MultipartFile file;
}
