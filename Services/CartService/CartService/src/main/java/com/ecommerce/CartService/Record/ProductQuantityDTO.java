package com.ecommerce.CartService.Record;

public record ProductQuantityDTO(
        String productId,
        String name,
        String description,
        String msn,
        float price,
        double quantity
) {
}
