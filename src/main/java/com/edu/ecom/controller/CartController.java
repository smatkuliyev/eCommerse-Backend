package com.edu.ecom.controller;

import com.edu.ecom.common.ApiResponse;
import com.edu.ecom.dto.cart.AddToCartDto;
import com.edu.ecom.model.Product;
import com.edu.ecom.model.User;
import com.edu.ecom.service.AuthenticationService;
import com.edu.ecom.service.CartService;
import com.edu.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("token") String token) {

        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);

        cartService.addToCart(addToCartDto, user);

        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }


}
