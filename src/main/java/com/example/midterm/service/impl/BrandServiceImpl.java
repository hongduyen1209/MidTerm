package com.example.midterm.service.impl;

import com.example.midterm.model.Brand;
import com.example.midterm.repository.BrandRepository;
import com.example.midterm.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository repository;

    @Override
    public Page<Brand> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Brand saveBrand(Brand brand) {
        brand.setId(0L);
        return repository.save(brand);
    }

    @Override
    public Brand updateBrand(Brand brand) {
        return repository.save(brand);
    }

    @Override
    public Page<Brand> findByName(String name, Pageable pageable) {
        return repository.findBrandsByName(name,pageable);
    }

    @Override
    public Brand findBrandById(Long id) {
        Optional<Brand> brand = repository.findById(id);
        if(brand.isPresent()){
            return brand.get();
        }
        return null;
    }

    @Override
    public List<Brand> findAllBrand() {
        return repository.findAll();
    }
}
