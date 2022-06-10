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

    @GetMapping("/newProducts")
    public List<Product> getNewProducts() {
        return productService.findNewProducts();
    }

    @GetMapping("")
    public List<Product> getProducts() {
        return productService.findAllProducts();
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PostMapping("create")
    public Product createProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return product;
    }

}
