package com.example.mainserver.category.service;

import com.example.mainserver.category.model.Category;

public interface CategoryService {
//    List<CategoryDto> getCategories(int from, int size);
//
//    CategoryDto getCategory(Long id);
//
    Category updateCategory(Category categoryDto, Long catId);

    Category createCategory(Category category);

    void deleteCategory(Long id);
}
