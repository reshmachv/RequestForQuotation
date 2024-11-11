package com.ecommerce.SupplierService.Model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@Data
public class Address {
    private String companyName;
    private String phoneNumber;
    private String area;
    private String landMark;
    private String pinCode;
    private String state;
}
