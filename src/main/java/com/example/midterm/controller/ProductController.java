package com.example.midterm.controller;

import com.example.midterm.model.*;
import com.example.midterm.service.BrandService;
import com.example.midterm.service.CategoryService;
import com.example.midterm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.text.NumberFormat;
import java.util.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final int maxUploadSizeInMb = 5 * 1024 * 1024;

    @Autowired
    private ProductService service;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showListProduct(
            Model model,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Product> products = new ArrayList<>();
        Pageable paging = PageRequest.of(page - 1, size);

        Page<Product> productPage;
        if("-1".equals(status)){
            status = null;
        }
        if ((name != null && !name.trim().isEmpty()) ||
                (code != null && !code.trim().isEmpty()) ||
                (status != null && !status.trim().isEmpty())
        ) {

            productPage = service.findByCodeAndNameAndStatus(code, name, status, paging);

        } else {
            productPage = service.findAll(paging);
        }
        products = productPage.getContent();
        Map<String, String> colors = new HashMap<>();
        colors.put("red", "Đỏ");
        colors.put("blue", "Xanh");
        colors.put("black", "Đen");
        colors.put("white", "Trắng");
        colors.put("pink", "Hồng");

        List<ProductDTO> dtos = new ArrayList<>();
        for(Product p : products){
            ProductDTO dto = new ProductDTO();
            for(Map.Entry<String, String> entry : colors.entrySet()){
                if(p.getColor().equals(entry.getKey())){
                    p.setColor(entry.getValue());
                }
            }   
            String imgBase64 = Base64.getEncoder().encodeToString(p.getImgsrc());
            dto.setName(p.getName());
            dto.setCode(p.getCode());
            dto.setId(p.getId());
            dto.setColor(p.getColor());
            dto.setStatus(p.getStatus());
            dto.setDescription(p.getDescription());
            dto.setImgBase64(imgBase64);
            dto.setQuantity(p.getQuantity());
            dto.setOs(p.getOs());
            dto.setResolution(p.getResolution());
            dto.setRam(p.getRam());
            dto.setRom(p.getRom());
            dto.setCategory(p.getCategory());
            dto.setBrand(p.getBrand());
            Locale locale = new Locale("vi", "VN");
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
            String priceFormat = currencyFormatter.format(p.getPrice());
            dto.setPriceFormat(priceFormat);
            dtos.add(dto);
        }

        Map<String, String> statusOption = new HashMap<>();
        statusOption.put("-1", "Tất cả");
        statusOption.put("0", "Đang kinh doanh");
        statusOption.put("1", "Ngừng kinh doanh");
        model.addAttribute("statusOp", statusOption);
        model.addAttribute("products", dtos);
        model.addAttribute("currentPage", productPage.getNumber() + 1);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", productPage.getTotalPages());
        if(name != null && !name.trim().isEmpty()){
            model.addAttribute("searchName",name);
        }
        if(code != null && !code.trim().isEmpty()){
            model.addAttribute("searchCode",code);
        }
        if(status != null && !status.trim().isEmpty()){
            model.addAttribute("searchStatus",status);
        }else{
            model.addAttribute("searchStatus","-1");
        }


        return "product_management";
    }

    @GetMapping("add-product")
    public String showFormProduct(Model model) {
        Product product = new Product();
        List<Category> listCategory = new ArrayList<>();
        List<Brand> listBrand = new ArrayList<>();
        Map<String, String> statusOption = new HashMap<>();
        statusOption.put("0", "Đang kinh doanh");
        statusOption.put("1", "Ngừng kinh doanh");
        Map<String, String> colors = new HashMap<>();
        colors.put("red", "Đỏ");
        colors.put("blue", "Xanh");
        colors.put("black", "Đen");
        colors.put("white", "Trắng");
        colors.put("pink", "Hồng");
        listCategory = categoryService.findAllCategory();
        listBrand = brandService.findAllBrand();
        model.addAttribute("product", product);
        model.addAttribute("statusOption", statusOption);
        model.addAttribute("colors", colors);
        model.addAttribute("listCategory", listCategory);
        model.addAttribute("listBrand", listBrand);
        return "form_product";
    }

    @PostMapping("add-product")
    public String addBrand(@Valid Product product, BindingResult bindingResult,
                           Model model, @RequestParam(value = "image", required = true) MultipartFile file
    ) {
        try {
            if (bindingResult.hasErrors()) {
                return "form_product";
            }

            if (file.getSize() > maxUploadSizeInMb) {
                return "redirect:/product/add-product?errorS=1";
            }
            if (file.isEmpty()) {
                return "redirect:/product/add-product?errorE=1";
            }
            byte[] img = file.getBytes();
            product.setImgsrc(img);
            Product p = service.saveProduct(product);

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/product";
        }
        return "redirect:/product";
    }

    @GetMapping("view/{id}")
    public String showDetail(@PathVariable("id") long id, Model model){
        ProductDTO dto = new ProductDTO();
        Product p = service.findProductById(id);
        if(p != null){
            Map<String, String> colors = new HashMap<>();
            colors.put("red", "Đỏ");
            colors.put("blue", "Xanh");
            colors.put("black", "Đen");
            colors.put("white", "Trắng");
            colors.put("pink", "Hồng");
            for(Map.Entry<String, String> entry : colors.entrySet()){
                if(p.getColor().equals(entry.getKey())){
                    p.setColor(entry.getValue());
                }
            }
            String imgBase64 = Base64.getEncoder().encodeToString(p.getImgsrc());
            dto.setName(p.getName());
            dto.setCode(p.getCode());
            dto.setId(p.getId());
            dto.setColor(p.getColor());
            dto.setStatus(p.getStatus());
            dto.setDescription(p.getDescription());
            dto.setImgBase64(imgBase64);
            dto.setQuantity(p.getQuantity());
            dto.setOs(p.getOs());
            dto.setResolution(p.getResolution());
            dto.setRam(p.getRam());
            dto.setRom(p.getRom());
            Locale locale = new Locale("vi", "VN");
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
            String priceFormat = currencyFormatter.format(p.getPrice());
            dto.setPriceFormat(priceFormat);
            Category c = categoryService.findCategoryById(p.getCategory().getId());
            dto.setCategory(c);
            Brand b = brandService.findBrandById(p.getBrand().getId());
            dto.setBrand(b);
            model.addAttribute("product",dto);
            return "view_product";
        }else{
            return "redirect:/product";
        }

    }

    @GetMapping("edit/{id}")
    public String showFormUpdate(@PathVariable("id") long id, Model model) {
        Product p = service.findProductById(id);
        if (p != null) {
            List<Category> listCategory = new ArrayList<>();
            List<Brand> listBrand = new ArrayList<>();
            Map<String, String> statusOption = new HashMap<>();
            statusOption.put("0", "Đang kinh doanh");
            statusOption.put("1", "Ngừng kinh doanh");
            Map<String, String> colors = new HashMap<>();
            colors.put("red", "Đỏ");
            colors.put("blue", "Xanh");
            colors.put("black", "Đen");
            colors.put("white", "Trắng");
            colors.put("pink", "Hồng");
            listCategory = categoryService.findAllCategory();
            listBrand = brandService.findAllBrand();
            model.addAttribute("product", p);
            model.addAttribute("statusOption", statusOption);
            model.addAttribute("colors", colors);
            model.addAttribute("listCategory", listCategory);
            model.addAttribute("listBrand", listBrand);
            return "form_update_product";
        } else {
            return "redirect:/product";
        }
    }


    @PostMapping("edit/{id}")
    public String editProduct(@PathVariable("id") long id,
                               @Valid Product product,
                               BindingResult bindingResult,
                               Model model,
                               @RequestParam(value="image", required = false) MultipartFile file){
        try{
            if(bindingResult.hasErrors()){
                return "form_update_product";
            }
            if(file != null && !file.isEmpty()){
                byte[] img = file.getBytes();
                product.setImgsrc(img);
                Product b = service.updateProduct(product);
            }else{
                Product pro = service.findProductById(product.getId());
                product.setImgsrc(pro.getImgsrc());
                Product b = service.updateProduct(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/product";
    }




}
