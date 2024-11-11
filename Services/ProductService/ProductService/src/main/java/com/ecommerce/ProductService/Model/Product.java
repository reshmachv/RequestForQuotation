package com.ecommerce.ProductService.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
@Data
public class Product {
    @Transient
    public static final String SEQUENCE_NAME = "product_sequence";
    @Id
    private String productId;
    @NotNull(message = "Product name can't be null!")
    @NotBlank(message="Product name can't be blank!")
    private String productName;
    @Positive(message = "price can't be negative!")
    private Float price;
    @DBRef
    private Catalog catalog;
}
