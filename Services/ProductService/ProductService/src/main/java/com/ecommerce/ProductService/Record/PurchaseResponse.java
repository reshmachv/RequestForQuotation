package com.ecommerce.ProductService.Record;

public record PurchaseResponse(
        String productId,
        String name,
        String description,
        String msn,
        float price,
        double quantity
) {
}
