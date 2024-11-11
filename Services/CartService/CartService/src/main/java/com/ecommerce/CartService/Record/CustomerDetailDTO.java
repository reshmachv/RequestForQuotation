package com.ecommerce.CartService.Record;

public record CustomerDetailDTO(
        String customerId,
        String Fname,
        String Lname,
        String email,
        String companyName,
        String phoneNumber,
        String area,
        String landMark,
        String pinCode,
        String state
){}
