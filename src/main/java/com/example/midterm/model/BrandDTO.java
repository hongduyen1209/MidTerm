package com.example.midterm.model;

public class BrandDTO {
    private Long id;
    private String name;
    private String base64Img;

    public BrandDTO() {
    }

    public BrandDTO(Long id, String name, String base64Img) {
        this.id = id;
        this.name = name;
        this.base64Img = base64Img;
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

    public String getBase64Img() {
        return base64Img;
    }

    public void setBase64Img(String base64Img) {
        this.base64Img = base64Img;
    }
}
