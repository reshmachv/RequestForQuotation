package com.ecommerce.ProductService.Record;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
        String productId,
        @NotNull(message = "Product name can't be null!")
        @NotBlank(message="Product name can't be blank!")
        String productName,
        @Positive(message = "price can't be negative!")
        Float price,
        @NotNull(message = "Msn can't be null!")
        @NotBlank(message="Msn can't be blank!")
        String msn
) {
}
