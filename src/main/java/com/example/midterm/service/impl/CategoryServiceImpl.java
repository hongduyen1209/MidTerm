package com.example.midterm.service.impl;

import com.example.midterm.model.Category;
import com.example.midterm.repository.CategoryRepository;
import com.example.midterm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Category saveCategory(Category category) {
        category.setId(0L);
        return repository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return repository.save(category);
    }

    @Override
    public Page<Category> findByName(String name, Pageable pageable) {
        return repository.findCategoriesByName(name,pageable);
    }

    @Override
    public Category findCategoryById(Long id) {
        Optional<Category> category = repository.findById(id);
        if(category.isPresent()){
            return category.get();
        }
        return null;
    }

    @Override
    public List<Category> findAllCategory() {
        return repository.findAll();
    }


}
