package com.heliosgems.model.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String name;
    private String description;

//    đường dẫn đến hình ảnh sau này sẽ dùng aws s3 để lưu ảnh
//    private String image;


    private Long price;
    private Long quantity;
    private String unit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_uuid", nullable = false)
    private Category category;

    private byte[] byteImg;
    private MultipartFile file;
}