package com.example.mainserver.category.service;

import com.example.mainserver.category.model.Category;
import com.example.mainserver.category.repository.CategoryRepository;
import com.example.mainserver.event.EventRepository;
import com.example.mainserver.event.model.Event;
import com.example.mainserver.exceptions.CategoryNotFounfExeption;
import com.example.mainserver.exceptions.WrongCategoryNameException;
import com.example.mainserver.exceptions.WrongPatchException;
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
    private final EventRepository eventRepository;

    @Override
    public Category updateCategory(Category categoryDto, Long catId) {
        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new CategoryNotFounfExeption("Category with id = " + catId + " not found"));

        Category categoryName = categoryRepository.getCategoryByName(categoryDto.getName());
        if (categoryName != null) {

            throw new WrongPatchException("уже существует категория с таким именем");
        }

        if (categoryDto.getName() == null) {
            throw new WrongCategoryNameException("имя категории не должно быть пустым");
        }
        category.setName(categoryDto.getName());

        return categoryRepository.save(category);
    }

    @Override
    public Category createCategory(Category category) {

        Category categoryName = categoryRepository.getCategoryByName(category.getName());
        if (categoryName != null) {

            throw new WrongPatchException("уже существует категория с таким именем");
        }
        if (category.getName() == null) {
            throw new WrongCategoryNameException("Field: name. Error: must not be blank. Value: null");
        }

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFounfExeption("Category with id = " + id + " not found"));

        List<Event> events = eventRepository.findByCategoryId(category.getId());
        if (!events.isEmpty()) {

            throw new WrongPatchException(" нельзя удалить категорию  связанную с событиями");
        }


        categoryRepository.delete(category);
    }

    @Override
    public List<Category> getCategories(int from, int size) {
        Pageable pageable = PageRequest.of(from, size, Sort.by(Sort.Direction.ASC, "id"));
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage.getContent();
    }

    @Override
    public Category getCategory(Long catId) {
        return categoryRepository.findById(catId)
                .orElseThrow(() -> new CategoryNotFounfExeption("Category with id = " + catId + " not found"));



    }
}
