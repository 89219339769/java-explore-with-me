package com.example.mainserver.category.repository;

import com.example.mainserver.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CategoryRepository extends JpaRepository<Category, Long> {


    @Query("select c from Category c where c.name = ?1 ")
    Category getCategoryByName(String name);
}
