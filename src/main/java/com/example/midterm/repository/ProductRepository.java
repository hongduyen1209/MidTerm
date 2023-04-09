package com.example.midterm.repository;

import com.example.midterm.model.Category;
import com.example.midterm.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
//    Page<Product> findProductByName(String name, Pageable pageable);
//    Page<Product> findProductByPrice(double price, Pageable pageable);
//    Page<Product> findProductByStatus(int status, Pageable pageable);

    @Query(value = "SELECT * FROM product p WHERE (:name is null or p.name like %:name% or :name = '') and (:code is null"
            + " or p.code like %:code% or :code = '') and (:status is null or p.status = :status or :status = '')",nativeQuery = true)
    Page<Product> findProductsByCodeAndNameAndStatus(String code,
                                                     String name,
                                                     String status,
                                                     Pageable pageable);

    @Query(value = "SELECT * FROM product p WHERE (:color is null or :color ='' or :color = '-1' or p.color = :color) and (:brandId is null"
            +" or p.brand_id = :brandId or :brandId='' or :brandId='-1') and (:categoryId is null or p.category_id = :categoryId or :categoryId = '-1') and (:priceFrom <=0"
            +" or p.price >= :priceFrom) and (:priceTo <= 0 or p.price <= :priceTo) and (:name is null or :name ='' or p.name like :name%)",nativeQuery = true)
    Page<Product> findProductsByColorAndCategoryAndBrandAndPrice(String name,String color,
                                                     String brandId,
                                                     String categoryId,
                                                     double priceFrom,
                                                     double priceTo,
                                                     Pageable pageable);

    @Query(value = "SELECT * FROM product p WHERE p.brand_id = :brandId",nativeQuery = true)
    Page<Product> findProductsByBrand(Long brandId,Pageable pageable);

    @Query(value = "SELECT * FROM product p WHERE p.category_id = :categoryId",nativeQuery = true)
    Page<Product> findProductsByCategory(Long categoryId, Pageable pageable);
}
