package com.edu.ecom.dto.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class CartDto {
    private List<CartItemDto> cartItems;
    private double totalCost;
}
