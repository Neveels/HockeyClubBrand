package com.ClubProduction.spring.controllers;

import com.ClubProduction.spring.models.Cart;
import com.ClubProduction.spring.models.Product;
import com.ClubProduction.spring.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/getAllCart")
    public List<Cart> getAllCart() {
        return cartService.findAllCart();
    }

    @PostMapping("/addProductToCart/{user_id}/{product_id}")
    public String addProductToCart(@PathVariable Long user_id, @PathVariable Long product_id) {
        cartService.addProductToCart(user_id, product_id);
        return "Product successfully added to cart";
    }

    @GetMapping("/getCartByUserId/{user_id}")
    public List<Cart> getUsersCart(@PathVariable Long user_id) {
        return cartService.getUsersCart(user_id);
    }

    @DeleteMapping("/delete/{user_id}/{product_id}")
    public boolean deleteProductFromCartById(@PathVariable Long user_id, @PathVariable Long product_id) {
        return cartService.deleteProductFromCartById(user_id, product_id);
    }
}
