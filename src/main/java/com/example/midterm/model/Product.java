package com.example.midterm.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Valid
    @NotEmpty(message = "Không được để trống")
    @Size(max = 255,message = "Không vượt quá 255 ký tự")
    private String code;

    @Valid
    @NotEmpty(message = "Không được để trống")
    @Size(max = 255,message = "Không vượt quá 255 ký tự")
    private String name;

    @Valid
    @Size(max = 255,message = "Không vượt quá 255 ký tự")
    private String description;

    @Valid
    @DecimalMin(value = "0.0", inclusive = false)
    private double price;

    @Valid
    @NotEmpty(message = "Không được để trống")
    @Size(max = 255,message = "Không vượt quá 255 ký tự")
    private String color;

    @Valid
    @Min(value = 0)
    private int quantity;

    @Valid
    @Min(value = 0)
    private int ram;

    @Valid
    @NotNull
    @Min(value = 0)
    private int rom;

    @Valid
    @NotEmpty(message = "Không được để trống")
    @Size(max = 255,message = "Không vượt quá 255 ký tự")
    private String os;

    @Valid
    @NotEmpty(message = "Không được để trống")
    @Size(max = 255,message = "Không vượt quá 255 ký tự")
    private String resolution;

    @Valid
    @NotEmpty
    private String status;

    @Lob
    @Column(name="imgsrc",columnDefinition = "MEDIUMBLOB")
    private byte[] imgsrc;

    @Valid
    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Valid
    @NotNull
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;


    public Product() {
    }

    public Product(Long id, String code, String name, String description, double price, String color, int quantity, int ram, int rom, String os, String resolution, String status, byte[] imgsrc) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.color = color;
        this.quantity = quantity;
        this.ram = ram;
        this.rom = rom;
        this.os = os;
        this.resolution = resolution;
        this.status = status;
        this.imgsrc = imgsrc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getRom() {
        return rom;
    }

    public void setRom(int rom) {
        this.rom = rom;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public byte[] getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(byte[] imgsrc) {
        this.imgsrc = imgsrc;
    }
}
