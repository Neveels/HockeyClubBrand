package com.HockeyClub.spring.repository;

import com.HockeyClub.spring.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitle(String title);
    @Query(
            value = "SELECT * FROM product ORDER BY date_of_added DESC LIMIT 2",
            nativeQuery = true
    )
    List<Product> findNew3Product();
}
