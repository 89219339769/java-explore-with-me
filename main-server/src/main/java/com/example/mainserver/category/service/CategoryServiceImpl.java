package com.example.mainserver.category.service;


import com.example.mainserver.category.model.Category;
import com.example.mainserver.category.repository.CategoryRepository;
import com.example.mainserver.compilation.model.Compilation;
import com.example.mainserver.exceptions.CategoryNotFounfExeption;
import com.example.mainserver.exceptions.CompilationNotFounfExeption;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
//    private final EventRepository eventRepository;

//    public CategoryServiceImpl(CategoryRepository categoryRepository) {
//        this.categoryRepository = categoryRepository;
//    //    this.eventRepository = eventRepository;
//    }

    //   @Override
//    public List<CategoryDto> getCategories(int from, int size) {
//        return categoryRepository.findAll(PageRequest.of(from / size, size))
//                .stream()
//                .map(CategoryMapper::toCategoryDto)
//                .collect(Collectors.toList());
//    }

//    @Override
//    @Transactional(readOnly = true)
//    public CategoryDto getCategory(Long id) {
//        return CategoryMapper.toCategoryDto(getAndCheckCategory(id));
//    }

    @Override
    public Category updateCategory(Category categoryDto, Long catId) {
        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new CategoryNotFounfExeption("Category with id = " + catId + " not found"));


        category.setName(categoryDto.getName());
        return categoryRepository.save(category);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFounfExeption("Category with id = " + id + " not found"));


        categoryRepository.delete(category);
    }

    @Override
    public List<Category> getCategories(int from, int size) {
        Pageable pageable = PageRequest.of(from, size, Sort.by(Sort.Direction.ASC, "id"));
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        List<Category> categoryList = categoryPage.getContent();
        return categoryList;
    }

    @Override
    public Category getCategory(Long catId) {
        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new CategoryNotFounfExeption("Category with id = " + catId + " not found"));
        return category;
    }
}
