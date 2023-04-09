package com.example.midterm.service;

import com.example.midterm.model.Category;
import com.example.midterm.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    Page<Product> findByCodeAndNameAndStatus(String code,String name,
                                                     String status,
                                                     Pageable pageable);
    Product findProductById(Long id);
    Page<Product> findByCategoryId(Long id,Pageable pageable);
    Page<Product> findByBrandId(Long id,Pageable pageable);
    List<Product> findAllProduct();
    Page<Product> findProductsByColorAndCategoryAndBrandAndPrice(String name,String color,
                                                                 String brandId,
                                                                 String categoryId,
                                                                 double priceFrom,
                                                                 double priceTo,
                                                                 Pageable pageable);
}
