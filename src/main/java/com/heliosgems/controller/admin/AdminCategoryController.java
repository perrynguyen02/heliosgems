package com.heliosgems.controller.admin;

import com.heliosgems.model.dto.CategoryDto;
import com.heliosgems.model.entity.Category;
import com.heliosgems.services.admin.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/category")
public class AdminCategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Category createCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.create(categoryDto);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }
}
