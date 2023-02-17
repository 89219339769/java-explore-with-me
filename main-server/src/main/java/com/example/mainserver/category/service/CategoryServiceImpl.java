package com.example.mainserver.category.service;


import com.example.mainserver.category.model.Category;
import com.example.mainserver.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

//    @Override
//    public CategoryDto updateCategory(CategoryDto categoryDto) {
//        Category category = getAndCheckCategory(categoryDto.getId());
//        category.setName(categoryDto.getName());
//        return CategoryMapper.toCategoryDto(categoryRepository.save(category));
//    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

 //   @Override
//    public void deleteCategory(Long id) {
////        if (!eventRepository.findAllByCategoryId(id).isEmpty()) {
////            throw new BadRequestException("only category without event can be delete");
////        }
//        categoryRepository.delete(getAndCheckCategory(id));
//    }

//    private Category getAndCheckCategory(Long id) {
//        return categoryRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("Category with id = " + id + " not found"));
//    }

}
