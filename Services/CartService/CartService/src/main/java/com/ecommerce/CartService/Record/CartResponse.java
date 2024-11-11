package com.ecommerce.CartService.Record;

import com.ecommerce.CartService.Model.CartItems;

import java.util.HashMap;
import java.util.List;

public record CartResponse(
       String customerId,
       HashMap<String, CartItems> productList,
       float totalPrice
) {

}
