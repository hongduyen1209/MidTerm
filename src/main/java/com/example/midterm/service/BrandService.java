package com.example.midterm.service;

import com.example.midterm.model.Brand;
import com.example.midterm.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BrandService {
    Page<Brand> findAll(Pageable pageable);
    Brand saveBrand(Brand brand);
    Brand updateBrand(Brand brand);
    Page<Brand> findByName(String name,Pageable pageable);
    Brand findBrandById(Long id);
    List<Brand> findAllBrand();
}
