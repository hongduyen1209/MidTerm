package com.example.midterm.controller;

import com.example.midterm.model.Brand;
import com.example.midterm.model.BrandDTO;
import com.example.midterm.model.Category;
import com.example.midterm.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/brand")
public class BrandController {

    private int maxUploadSizeInMb = 5 * 1024 * 1024;

    @Autowired
    private BrandService service;

    @GetMapping
    public String showListBrand(
            Model model,
            @RequestParam(value = "name",required = false)String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size){
        List<Brand> brands = new ArrayList<>();
        Pageable paging = PageRequest.of(page-1, size);

        Page<Brand> brandPage;
        if(name != null && !name.trim().isEmpty()){
            brandPage = service.findByName(name,paging);
        }else{
            brandPage = service.findAll(paging);
        }
        brands = brandPage.getContent();
        List<BrandDTO> dtos = new ArrayList<>();
        if(brands.size() > 0){
            for(Brand b:brands){
                BrandDTO dto = new BrandDTO();
                dto.setId(b.getId());
                dto.setName(b.getName());
                String imgBase64 = Base64.getEncoder().encodeToString(b.getImgsrc());
                dto.setBase64Img(imgBase64);
                dtos.add(dto);
            }
        }
        model.addAttribute("brands",dtos);
        model.addAttribute("currentPage", brandPage.getNumber() + 1);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", brandPage.getTotalPages());

        return "brand_management";
    }

    @GetMapping("add-brand")
    public String showFormBrand(Model model){
        Brand brand = new Brand();
        model.addAttribute("brand",brand);
        return "form_brand";
    }

    @PostMapping("add-brand")
    public String addBrand(@Valid Brand brand, BindingResult bindingResult,
                           Model model,@RequestParam(value = "image",required = true) MultipartFile file
                           ){
        try{
            if(bindingResult.hasErrors()){
                return "form_brand";
            }

            if(file.getSize() > maxUploadSizeInMb){
                return "redirect:/brand/add-brand?errorS=1";
            }
            if(file.isEmpty()){
                return "redirect:/brand/add-brand?errorE=1";
            }
            byte[] img = file.getBytes();
            brand.setImgsrc(img);
            Brand b = service.saveBrand(brand);

        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/brand";
        }
        return "redirect:/brand";
    }

    @GetMapping("edit/{id}")
    public String showFormUpdate(@PathVariable("id") long id, Model model){
        Brand b = service.findBrandById(id);
        if(b != null){
            model.addAttribute("brand",b);
            return "form_update_brand";
        }else{
            return "redirect:/brand";
        }
    }

    @PostMapping("edit/{id}")
    public String editBrand(@PathVariable("id") long id,
                               @Valid Brand brand,
                               BindingResult bindingResult,
                               Model model,
                               @RequestParam(value="image", required = false) MultipartFile file){
        try{
            if(bindingResult.hasErrors()){
                return "form_update_brand";
            }
            if(file != null && !file.isEmpty()){
                byte[] img = file.getBytes();
                brand.setImgsrc(img);
                Brand b = service.updateBrand(brand);
            }else{
                Brand bra = service.findBrandById(brand.getId());
                brand.setImgsrc(bra.getImgsrc());
                Brand b = service.updateBrand(brand);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/brand";
    }
}
