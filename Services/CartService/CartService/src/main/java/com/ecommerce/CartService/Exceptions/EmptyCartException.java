package com.ecommerce.CartService.Exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmptyCartException extends RuntimeException{
    private final String msg;
}
