package com.edu.ecom.service;

import com.edu.ecom.dto.cart.AddToCartDto;
import com.edu.ecom.model.Cart;
import com.edu.ecom.model.Product;
import com.edu.ecom.model.User;
import com.edu.ecom.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CartService {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartRepository cartRepository;

    public void addToCart(AddToCartDto addToCartDto, User user) {
        Product product = productService.findById(addToCartDto.getProductId());
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
    }
}
