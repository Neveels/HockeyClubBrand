package com.HockeyClub.spring.controllers;

import com.HockeyClub.spring.models.Product;
import com.HockeyClub.spring.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
//@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class ProductController {
    private final ProductService productService;
    //final поля всегда должны быть инициализированы, поэтому создаем констурктор RequiredArgsConstructor

    @GetMapping("newProducts")
    public List<Product> products(){
        return productService.first3Products();
    }

    @GetMapping("allProduct")
    public List<Product> allProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model){
        model.addAttribute("product",productService.getProductById(id));
        return "product-info";
    }

    @PostMapping("create")
    public Product createProduct(@RequestBody Product product){
        productService.saveProduct(product);
        return product;
    }
//
//    @PostMapping("create")
//    public String createProduct(MultipartFile file, Product product) throws IOException {
//        productService.saveProduct(product, file);
//        return "redirect:/";
//    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "redirect:/";
    }

}
