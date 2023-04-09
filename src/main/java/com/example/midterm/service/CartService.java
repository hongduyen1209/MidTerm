package com.example.midterm.service;

import com.example.midterm.model.DetailDTO;

import java.util.Collection;

public interface CartService {

    void add(DetailDTO dto);
    void remove(long id);
    Collection<DetailDTO> getAll();
    String getTotal();
    double gTotal();
    void clearAll();
}
