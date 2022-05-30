package com.ClubProduction.spring.controllers;

import com.ClubProduction.spring.models.Product;
import com.ClubProduction.spring.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService productService;

    @GetMapping("newProducts")
    public List<Product> products(){
        return productService.first3Products();
    }

    @GetMapping("allProduct")
    public List<Product> allProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id){
        productService.deleteProduct(id);
        return "product successfully deleted";
    }

    @PostMapping("create")
    public Product createProduct(@RequestBody Product product){
        productService.saveProduct(product);
        return product;
    }

}
