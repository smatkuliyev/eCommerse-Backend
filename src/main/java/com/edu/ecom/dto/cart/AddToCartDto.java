package com.edu.ecom.dto.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Setter
@Getter
public class AddToCartDto {

    private Integer id;

    @NotNull
    private Integer productId;

    @NotNull
    private Integer quantity;

}
