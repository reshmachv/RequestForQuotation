package com.ecommerce.CustomerService.Model;


import java.util.List;

public record CustomerResponse(
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
) {
}
