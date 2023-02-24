package com.example.mainserver.category.controller;

import com.example.mainserver.category.model.Category;
import com.example.mainserver.category.service.CategoryService;
import com.example.mainserver.event.model.EventDtoShort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping()
@AllArgsConstructor
public class CategoryControllerPublic {
private  final CategoryService categoryService;


    @GetMapping("/categories")
    public List<Category> getCategories(@RequestParam (defaultValue = "0") int from, @RequestParam(defaultValue = "10") int size) {
        return categoryService.getCategories(from, size);
    }

    @GetMapping("/categories/{catId}")
    public Category getCategoryById(@PathVariable Long catId) {
        return categoryService.getCategory(catId);
    }


}
