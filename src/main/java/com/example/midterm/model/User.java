package com.example.midterm.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Valid
    @NotEmpty(message = "Không được để trống")
    private String firstName;

    @NotEmpty(message = "Không được để trống")
    private String lastName;
    @NotEmpty(message = "Không được để trống")
    private String address;
    @NotEmpty(message = "Không được để trống")
    private String state;
    @NotEmpty(message = "Không được để trống")
    private String ward;
    @NotEmpty(message = "Không được để trống")
    private String city;
    @NotEmpty(message = "Không được để trống")
    private String phoneNum;
    @NotEmpty(message = "Không được để trống")
    @Email(message = "Vui lòng nhập đúng định dạng")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Order> orders;

    public User() {
    }

    public User(Long id, String firstName, String lastName, String address, String state, String ward, String city, String phoneNum, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.state = state;
        this.ward = ward;
        this.city = city;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
