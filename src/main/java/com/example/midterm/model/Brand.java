package com.example.midterm.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Blob;
import java.util.Arrays;
import java.util.Set;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Valid
    @NotEmpty(message = "Không được để trống")
    @Size(max = 255,message = "Không vượt quá 255 ký tự")
    private String name;

    @Lob
    @Column(name="imgsrc",columnDefinition = "MEDIUMBLOB")
    private byte[] imgsrc;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private Set<Product> products;

    public Brand() {
    }

    public Brand(Long id, String name, byte[] imgsrc) {
        this.id = id;
        this.name = name;
        this.imgsrc = imgsrc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(byte[] imgsrc) {
        this.imgsrc = imgsrc;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

}
