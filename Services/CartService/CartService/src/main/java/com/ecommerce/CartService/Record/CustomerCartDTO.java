package com.ecommerce.CartService.Record;

import com.ecommerce.CartService.Model.CartItems;

import java.util.HashMap;
import java.util.List;

public record CustomerCartDTO(
        String customerId,
        String emailId,
        String companyName,
        String phoneNumber,
        String area,
        String landMark,
        String pinCode,
        String state,
        HashMap<String, CartItems> productList,
        float totalPrice
) {
}
