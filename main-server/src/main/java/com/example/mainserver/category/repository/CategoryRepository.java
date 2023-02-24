package com.example.mainserver.category.repository;

import com.example.mainserver.category.model.Category;
import com.example.mainserver.event.model.Event;
import com.example.mainserver.user.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {



    @Query("select c from Category c where c.name = ?1 ")
    Category getCategoryByName(String name);
}
