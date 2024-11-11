package com.ecommerce.CustomerService.Model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

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
