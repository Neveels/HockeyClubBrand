package com.ClubProduction.spring.repository;

import com.ClubProduction.spring.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUserId(Long user);

    void deleteByProductId(Long product_id);

}
