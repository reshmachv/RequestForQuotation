package com.ecommerce.ProductService.Record;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record PurchaseRequest(
        @NotNull(message="productId can't be null!")
        String productId,
        @Positive(message="quantity can't be 0 or negative!")
        double quantity
) {
}
