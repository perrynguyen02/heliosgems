package com.heliosgems.services.admin;

import com.heliosgems.model.dto.ProductDto;
import com.heliosgems.model.entity.Product;
import com.heliosgems.repository.ProductRepo;
import com.heliosgems.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService implements GeneralService<Product, ProductDto> {
    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Product findById(UUID uuid) {
        return productRepo.findById(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Product create(ProductDto productDto) {
        return null;
    }

    @Override
    public Product update(UUID uuid, ProductDto productDto) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }
}
