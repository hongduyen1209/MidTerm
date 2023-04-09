package com.example.midterm.service.impl;

import com.example.midterm.model.DetailDTO;
import com.example.midterm.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@SessionScope
@Service
public class CartServiceImpl implements CartService {

    Map<Long,DetailDTO> cart = new HashMap<>();

    @Autowired
    private CartService cartService;

    @Override
    public void add(DetailDTO dto) {
        DetailDTO detailDTO = cart.get(dto.getId());
        if(detailDTO!=null){
            detailDTO.setQuantity(dto.getQuantity()+dto.getQuantity());
            cart.put(detailDTO.getId(),detailDTO);
        }else{
            cart.put(dto.getId(),dto);
        }
    }

    @Override
    public void remove(long id) {
        cart.remove(id);
    }


    @Override
    public Collection<DetailDTO> getAll() {
        return cart.values();
    }

    @Override
    public String getTotal() {
        double rs = cart.values().stream().mapToDouble(item->item.getQuantity() * item.getPrice()).sum();
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String priceFormat = currencyFormatter.format(rs);
        return priceFormat;
    }

    @Override
    public double gTotal() {
        double rs = cart.values().stream().mapToDouble(item->item.getQuantity() * item.getPrice()).sum();
        return rs;
    }

    @Override
    public void clearAll() {
        cart.clear();
    }
}
