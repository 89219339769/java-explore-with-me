package com.example.mainserver.category.controller;


import com.example.mainserver.category.model.Category;
import com.example.mainserver.category.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
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
    public Category createCategory(@RequestBody Category category, HttpServletResponse response) {


        response.setStatus(CREATED.value());
        log.info("create category");
        return categoryService.createCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id, HttpServletResponse response) {
        log.info("delete category with id {}", id);

        response.setStatus(NO_CONTENT.value());
        categoryService.deleteCategory(id);
    }
}