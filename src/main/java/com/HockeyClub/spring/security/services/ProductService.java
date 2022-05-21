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

//    public void saveProduct(Product product, MultipartFile file) throws IOException {
//        Image image1 = new Image();
//        if(file.getSize() != 0){
//            image1 = toImageEntity(file);
//            product.addImage(image1);
//        }
////        log.info("Saving new{}", product.getTitle());
////        Product productFromDb = productRepository.save(product);
////        productFromDb.setImage(productFromDb.getImage());
//        productRepository.save(product);
//    }

//    private Image toImageEntity(MultipartFile file) throws IOException {
//        Image image = new Image();
//        image.setName(file.getName());
//        image.setContentType(file.getContentType());
//        image.setSize(file.getSize());
//        image.setBytes(file.getBytes());
//        return image;
//    }

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

