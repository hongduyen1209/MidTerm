package com.example.midterm.service;

import com.example.midterm.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Page<Category> findAll(Pageable pageable);
    Category saveCategory(Category category);
    Category updateCategory(Category category);
    Page<Category> findByName(String name,Pageable pageable);
    Category findCategoryById(Long id);
    List<Category> findAllCategory();
}
