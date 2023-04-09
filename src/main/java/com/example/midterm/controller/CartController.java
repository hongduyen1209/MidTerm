package com.example.midterm.controller;

import com.example.midterm.model.*;
import com.example.midterm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService service;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public String showCart(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("listItem",cartService.getAll());
        model.addAttribute("total",cartService.getTotal());
        model.addAttribute("gTotal",cartService.gTotal());
        return "cart";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(HttpSession session, Model model, DetailDTO dto){
        cartService.add(dto);
        model.addAttribute("listItem",cartService.getAll());
        model.addAttribute("total",cartService.getTotal());
        model.addAttribute("gTotal",cartService.gTotal());
        return "cart";
    }

    @GetMapping("/clear-item/{id}")
    public String clearItem(@PathVariable("id") long id,Model model){
        cartService.remove(id);
        return "redirect:/cart";
    }

    @PostMapping("/check-out")
    public String checkOut(@Valid User user,Model model){

        User u = userService.saveUser(user);
        Collection<DetailDTO> dtos = cartService.getAll();
        double rs = dtos.stream().mapToDouble(item->item.getQuantity() * item.getPrice()).sum();

        Order o = new Order();
        o.setTotal(rs);
        o.setCreatedDate(new Date());
        o.setUser(u);
        Order order = orderService.saveOrder(o);

        Collection<DetailDTO> listDetail = cartService.getAll();
        for(DetailDTO d : listDetail){
            Product p = service.findProductById(d.getId());
            p.setQuantity(p.getQuantity()-d.getQuantity());
            if(p.getQuantity() == 0){
                p.setStatus("-1");
            }
            Product product = service.updateProduct(p);
            OrderDetailPK pk = new OrderDetailPK();
            pk.setOrder(order);
            pk.setProduct(p);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setPk(pk);
            orderDetail.setProductName(p.getName());
            orderDetail.setQuantity(d.getQuantity());
            orderDetail.setPrice(d.getPrice());
            orderDetailService.saveOrderDetail(orderDetail);
            //orderDetailService.saveOrderDetail(p.getId(),order.getId(),p.getPrice(),p.getName(),p.getQuantity());
        }
        cartService.clearAll();
        return "result-check-out";

    }


}
