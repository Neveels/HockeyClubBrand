package com.ClubProduction.spring.services;

import com.ClubProduction.spring.models.Product;
import com.ClubProduction.spring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Slf4j
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findNewProducts() {
        List<Product> products = productRepository.findNewProducts();
        return products;
    }

    public void saveProduct(Product product) {
        log.info("Saving new{}", product);
        productRepository.save(product);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}

