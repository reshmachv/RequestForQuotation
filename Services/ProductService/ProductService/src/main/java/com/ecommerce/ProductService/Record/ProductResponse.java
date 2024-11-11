package com.ecommerce.ProductService.Record;

import java.util.List;

public record ProductResponse(
        String productId,
        String productName,
        Float price,
        String msn
) {
}
