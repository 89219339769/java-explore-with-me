package com.example.mainserver.category.repository;

import com.example.mainserver.category.model.Category;
import com.example.mainserver.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
