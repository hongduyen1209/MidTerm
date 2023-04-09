package com.example.midterm.controller;

import com.example.midterm.model.*;
import com.example.midterm.service.BrandService;
import com.example.midterm.service.CartService;
import com.example.midterm.service.CategoryService;
import com.example.midterm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.NumberFormat;
import java.util.*;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public String home(Model model){
        Pageable paging = PageRequest.of(0, 5);
        Page<Brand> brandPage = brandService.findAll(paging);
        Page<Category> categoryPage = categoryService.findAll(paging);
        List<Brand> brands = brandPage.getContent();
        List<Category> categories = categoryPage.getContent();
        List<Product> products = productService.findAllProduct();
        //1-PHONE,2-LAPTOP,3-TABLET
        List<ProductDTO> dtos = new ArrayList<>();
        for(Product p:products){
            ProductDTO dto = new ProductDTO();
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
        model.addAttribute("categories",categories);
        model.addAttribute("brands",brands);
        model.addAttribute("products",dtos);
        return "home";
    }

    @GetMapping("/product-detail/{id}")
    public String viewDetail(Model model, @PathVariable("id") long id){
        DetailDTO detailDTO = new DetailDTO();
        Product p = productService.findProductById(id);
        ProductDTO dto = new ProductDTO();
        Map<String, String> colors = new HashMap<>();
        colors.put("red", "Đỏ");
        colors.put("blue", "Xanh");
        colors.put("black", "Đen");
        colors.put("white", "Trắng");
        colors.put("pink", "Hồng");
        for (Map.Entry<String, String> entry : colors.entrySet()) {
            if(entry.getKey().equals(p.getColor())){
                dto.setColor(entry.getValue());
            }
        }
        String imgBase64 = Base64.getEncoder().encodeToString(p.getImgsrc());
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setCode(p.getCode());
        dto.setOs(p.getOs());
        dto.setResolution(p.getResolution());
        dto.setRom(p.getRom());
        dto.setRam(p.getRam());
        dto.setQuantity(p.getQuantity());
        dto.setStatus(p.getStatus());
        dto.setDescription(p.getDescription());
        dto.setCategory(p.getCategory());
        dto.setBrand(p.getBrand());
        dto.setPrice(p.getPrice());
        dto.setImgBase64(imgBase64);
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String priceFormat = currencyFormatter.format(p.getPrice());
        dto.setPriceFormat(priceFormat);
        model.addAttribute("p",dto);
        model.addAttribute("detail",detailDTO);
        return "detail_product";
    }

    @GetMapping("/product-list")
    public String showListProduct(Model model,
                                  @RequestParam(value = "color", required = false) String color,
                                  @RequestParam(value = "brandId", required = false) String brandId,
                                  @RequestParam(value = "categoryId", required = false) String categoryId,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "priceFrom", required = false,defaultValue = "0") int priceFrom,
                                  @RequestParam(value = "priceTo", required = false,defaultValue = "0") int priceTo,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "10") int size){
        Map<String, String> colors = new HashMap<>();
        colors.put("-1", "Tất cả");
        colors.put("red", "Đỏ");
        colors.put("blue", "Xanh");
        colors.put("black", "Đen");
        colors.put("white", "Trắng");
        colors.put("pink", "Hồng");
        List<Product> products = new ArrayList<>();
        List<Category> categories = categoryService.findAllCategory();
        Category c = new Category();
        c.setId(-1L);
        c.setName("Tất cả");
        categories.add(0,c);
        List<Brand> brands = brandService.findAllBrand();
        Brand b = new Brand();
        b.setId(-1L);
        b.setName("Tất cả");
        brands.add(0,b);
        //Brand brand = brandService.findBrandById(Long.parseLong(brandId));
        //Category category = categoryService.findCategoryById(Long.parseLong(categoryId));
        Pageable paging = PageRequest.of(page - 1, size);

        Page<Product> productPage;
        if((color != null && !color.trim().isEmpty()) ||
                (brandId != null && !brandId.trim().isEmpty()) ||
                        (categoryId !=null && !categoryId.trim().isEmpty()) ||
                        (priceFrom > 0) || (priceTo > 0) || (name !=null && !name.trim().isEmpty())
                ){
            Long priceF = Long.valueOf(priceFrom);
            Long priceT = Long.valueOf(priceTo);
            productPage = productService.findProductsByColorAndCategoryAndBrandAndPrice(name,color,
                    brandId,
                    categoryId,priceF,priceT,paging);
        }else{
            productPage = productService.findAll(paging);
        }
        products = productPage.getContent();
        List<ProductDTO> dtos = new ArrayList<>();
        for(Product p : products){
            ProductDTO dto = new ProductDTO();
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
        model.addAttribute("products",dtos);
        model.addAttribute("colors",colors);
        model.addAttribute("brands",brands);
        model.addAttribute("categories",categories);
        model.addAttribute("currentPage", productPage.getNumber() + 1);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", productPage.getTotalPages());

        if (color != null && !color.trim().isEmpty()){
            model.addAttribute("color",color);
        }
        if(brandId != null && !brandId.trim().isEmpty()){
            model.addAttribute("brandIdSelect",Long.parseLong(brandId));
        }
        if(categoryId != null && !categoryId.trim().isEmpty()){
            model.addAttribute("categoryIdSelect",Long.parseLong(categoryId));
        }
        if(priceFrom > 0){
            model.addAttribute("priceFrom",priceFrom);
        }
        if(priceTo > 0){
            model.addAttribute("priceTo",priceTo);
        }
        return "product_all";
    }


}
