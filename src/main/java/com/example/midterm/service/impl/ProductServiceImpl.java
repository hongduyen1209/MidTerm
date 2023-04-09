package com.example.midterm.service.impl;

import com.example.midterm.model.Brand;
import com.example.midterm.model.Product;
import com.example.midterm.repository.ProductRepository;
import com.example.midterm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Product saveProduct(Product product) {
        product.setId(0L);
        return repository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Page<Product> findByCodeAndNameAndStatus(String code, String name, String status, Pageable pageable) {
        return repository.findProductsByCodeAndNameAndStatus(code,name,status,pageable);
    }


    @Override
    public Product findProductById(Long id) {
        Optional<Product> product = repository.findById(id);
        if(product.isPresent()){
            return product.get();
        }
        return null;
    }

    @Override
    public Page<Product> findByCategoryId(Long id,Pageable pageable) {
        return repository.findProductsByCategory(id,pageable);
    }

    @Override
    public Page<Product> findByBrandId(Long id,Pageable pageable) {
        return repository.findProductsByBrand(id,pageable);
    }

    @Override
    public List<Product> findAllProduct() {
        return repository.findAll();
    }

    @Override
    public Page<Product> findProductsByColorAndCategoryAndBrandAndPrice(String name,String color, String brandId, String categoryId, double priceFrom, double priceTo, Pageable pageable) {
        return repository.findProductsByColorAndCategoryAndBrandAndPrice(name,color,brandId,categoryId,priceFrom,priceTo,pageable);
    }

}
