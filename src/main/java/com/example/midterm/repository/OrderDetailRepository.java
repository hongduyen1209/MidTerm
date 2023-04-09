package com.example.midterm.repository;

import com.example.midterm.model.OrderDetail;
import com.example.midterm.model.OrderDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {
//
//    @Query(value = "INSERT INTO order_detail (product_id,order_id,quantity,price,product_name)" +
//            " values (:productId,:orderId,:quantity,:price,:name)",nativeQuery = true)
//    void saveOrderDetail(Long productId,Long orderId,double price,String name,int quantity);
}
