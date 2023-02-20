package com.example.mainserver.category.controller;


import com.example.mainserver.category.model.Category;
import com.example.mainserver.category.service.CategoryService;
import com.example.mainserver.exceptions.WrongNameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/admin/categories")
public class CategoryControllerAdmin {
    private final CategoryService categoryService;

    public CategoryControllerAdmin(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PatchMapping("/{catId}")
    public Category updateCategory(@Valid @RequestBody Category categoryDto, @PathVariable Long catId) {
        log.info("update category with id {}", categoryDto.getId());
        return categoryService.updateCategory(categoryDto, catId);
    }

    @PostMapping
    public Category createCategory(@Valid @RequestBody Category category) {

        if(category.getName().isBlank()){
            throw new WrongNameException("Field: name. Error: must not be blank. Value: null");
        }

        log.info("create category");
        return categoryService.createCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        log.info("delete category with id {}", id);
        categoryService.deleteCategory(id);
    }
}