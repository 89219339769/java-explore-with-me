package com.example.mainserver.category.service;

import com.example.mainserver.category.model.Category;

import java.util.List;

public interface CategoryService {

    Category updateCategory(Category categoryDto, Long catId);

    Category createCategory(Category category);

    void deleteCategory(Long id);

    List<Category> getCategories(int from, int size);

    Category getCategory(Long catId);
}
