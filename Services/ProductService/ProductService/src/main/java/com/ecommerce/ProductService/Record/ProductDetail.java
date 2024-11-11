package com.ecommerce.ProductService.Record;

public record ProductDetail(
        String productName,
        Float price,
        String msn,
        String category,
        String description
) {
}
