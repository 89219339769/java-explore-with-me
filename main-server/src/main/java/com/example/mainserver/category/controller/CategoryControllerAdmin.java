package com.example.mainserver.category.controller;


import com.example.mainserver.category.model.Category;
import com.example.mainserver.category.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/admin/categories")
public class CategoryControllerAdmin {
    private final CategoryService categoryService;

    public CategoryControllerAdmin(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

//    @PatchMapping
//    public CategoryDto updateCategory(@Valid @RequestBody CategoryDto categoryDto) {
//        log.info("update category with id {}", categoryDto.getId());
//        return categoryService.updateCategory(categoryDto);
//    }

    @PostMapping
    public Category createCategory(@Valid @RequestBody Category category) {
        log.info("create category");
        return categoryService.createCategory(category);
    }

//    @DeleteMapping("/{id}")
//    public void deleteCategory(@PathVariable Long id) {
//        log.info("delete category with id {}", id);
//        categoryService.deleteCategory(id);
//    }
}