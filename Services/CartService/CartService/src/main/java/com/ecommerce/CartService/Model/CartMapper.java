package com.ecommerce.CartService.Model;

import com.ecommerce.CartService.Record.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Component
public class CartMapper {

    public CartResponse toCartResponse(Cart cart){
        return new CartResponse(cart.getCustomerId(), cart.getCartItems() , cart.getTotal());
    }

    public Cart toCart(HashMap<String, CartItems> cartItems, float totalPrice, String customerId){
        return new Cart(customerId,cartItems,totalPrice, LocalDateTime.now(),LocalDateTime.now());
    }

    public CustomerCartDTO toCartDetail(Cart cart,CustomerDetailDTO customerDetail){
        return new CustomerCartDTO(cart.getCustomerId(), customerDetail.email(), customerDetail.companyName(), customerDetail.phoneNumber(),
                customerDetail.area(), customerDetail.landMark(), customerDetail.pinCode(), customerDetail.state(),
                cart.getCartItems(),cart.getTotal());
    }


}
