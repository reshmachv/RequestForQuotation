package com.ecommerce.CartService.Model;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CartItems {
    private String name;
    private String description;
    private String msn;
    @Positive(message = "price can't be negative")
    private float price;
    @Positive(message = "quantity can't be 0")
    private double quantity;
}
