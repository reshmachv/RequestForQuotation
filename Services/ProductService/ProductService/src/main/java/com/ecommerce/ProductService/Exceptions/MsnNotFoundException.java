package com.ecommerce.ProductService.Exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MsnNotFoundException extends RuntimeException{
    private final String msg;
}
