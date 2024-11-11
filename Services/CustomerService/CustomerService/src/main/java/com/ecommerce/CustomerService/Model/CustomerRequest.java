package com.ecommerce.CustomerService.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CustomerRequest(
        String customerId,
        @NotNull(message = "name is the mandatory field!")
        String Fname,
        String Lname,
        @NotNull(message = "Email can't be email!")
        @Email(message = "Email is not valid!")
        String email,
        @NotNull(message = "Password can't be null!")
        @NotBlank(message="Password can't be blank!")
        @Length(min=8)
        String password,
        @NotNull
        Address Address
) {
}
