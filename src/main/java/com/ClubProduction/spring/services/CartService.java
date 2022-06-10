package com.ClubProduction.spring.services;

import com.ClubProduction.spring.models.Cart;
import com.ClubProduction.spring.repository.CartRepository;
import com.ClubProduction.spring.repository.ProductRepository;
import com.ClubProduction.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public List<Cart> findAllCart() {
        return cartRepository.findAll();
    }

    public void addProductToCart(Long user_id, Long product_id) {
        Cart cart = new Cart();
        cart.setProduct(productRepository.getById(product_id));
        cart.setUser(userRepository.getById(user_id));
        cartRepository.save(cart);
    }

    public List<Cart> getUsersCart(Long user) {
        return cartRepository.findAllByUserId(user);
    }

    public boolean deleteProductFromCartById(Long user_id, Long product_id) {
        List<Cart> userCart = cartRepository.findAllByUserId(user_id);
        for (int i = 0; i < userCart.size(); i++) {
            if (userCart.get(i).getProduct().getId() == product_id) {
                cartRepository.deleteById(userCart.get(i).getId());
                return true;
            }
        }
        return false;
    }
}
