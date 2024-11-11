package com.ecommerce.RFQService.Record;

import org.springframework.data.annotation.Id;

public record CustomerDetails(
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
