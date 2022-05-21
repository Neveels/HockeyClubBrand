package com.HockeyClub.spring.security.services;

import com.HockeyClub.spring.models.Product;
import com.HockeyClub.spring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@Slf4j
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> listProducts(String title){
        List<Product> products = productRepository.findAll();
        if(title != null) return productRepository.findByTitle(title);
        else productRepository.findAll();
        return products;
    }

    public List<Product> first3Products(){
        List<Product> products = productRepository.findNew3Product();
        return  products;
    }

    public void saveProduct(Product product){
        log.info("Saving new{}", product);
        productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}

