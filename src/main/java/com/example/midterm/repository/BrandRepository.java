package com.example.midterm.repository;

import com.example.midterm.model.Brand;
import com.example.midterm.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {
    Page<Brand> findBrandsByName(String name, Pageable pageable);
}
