package com.ecommerce.RFQService.Record;

import com.ecommerce.RFQService.Model.CartItem;

import java.util.HashMap;

public record CartResponse(
        String customerId,
        HashMap<String, CartItem> productList,
        Float totalPrice
) {
}
