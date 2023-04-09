package com.example.midterm.controller;

import com.example.midterm.model.Category;
import com.example.midterm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public String showListCategory(
            Model model,
            @RequestParam(value = "name",required = false)String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size){
        List<Category> categories = new ArrayList<>();
        Pageable paging = PageRequest.of(page-1, size);

        Page<Category> categoryPage;
        if(name != null && !name.trim().isEmpty()){
            categoryPage = service.findByName(name,paging);
        }else{
            categoryPage = service.findAll(paging);
        }
        System.out.println(categoryPage.getTotalPages());
        categories = categoryPage.getContent();
        model.addAttribute("categories",categories);
        model.addAttribute("currentPage", categoryPage.getNumber() + 1);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", categoryPage.getTotalPages());

        return "category_management";
    }



    @GetMapping("add-category")
    public String showFormCategory(Model model){
        Category category = new Category();
        model.addAttribute("category",category);
        return "form_category";
    }

    @PostMapping("add-category")
    public String addCategory(@Valid Category category, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            return "form_category";
        }
        System.out.println(model);
        Category c = service.saveCategory(category);
        return "redirect:/category";
    }

    @GetMapping("edit/{id}")
    public String showFormUpdate(@PathVariable("id") long id, Model model){
        Category c = service.findCategoryById(id);
        if(c != null){
            model.addAttribute("category",c);
            return "form_update_category";
        }else{
            return "redirect:/category";
        }
    }

    @PostMapping("edit/{id}")
    public String editCategory(@PathVariable("id") long id,
                               @Valid Category category,
                               BindingResult bindingResult,
                               Model model){
        if(bindingResult.hasErrors()){
            return "form_update_category";
        }
        Category c = service.updateCategory(category);
        return "redirect:/category";
    }
}
