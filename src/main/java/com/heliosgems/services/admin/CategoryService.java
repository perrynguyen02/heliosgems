package com.heliosgems.services.admin;

import com.heliosgems.model.dto.CategoryDto;
import com.heliosgems.model.entity.Category;
import com.heliosgems.repository.CategoryRepo;
import com.heliosgems.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService implements GeneralService<Category, CategoryDto> {
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findById(UUID uuid) {
        return categoryRepo.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Category create(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public Category update(UUID uuid, CategoryDto categoryDto) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }
}
