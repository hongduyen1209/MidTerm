package com.example.midterm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class OrderDetail {

    @EmbeddedId
    @JsonIgnore
    private OrderDetailPK pk;

    private String productName;
    private double price;
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(Order order, Product product, int quantity, String productName, double price) {
        pk = new OrderDetailPK();
        pk.setOrder(order);
        pk.setProduct(product);
        this.quantity = quantity;
        this.productName = productName;
        this.price = price;
    }


    public OrderDetailPK getPk() {
        return pk;
    }

    public void setPk(OrderDetailPK pk) {
        this.pk = pk;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
