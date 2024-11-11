package com.ecommerce.SupplierService.Exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SupplierNotFoundException extends RuntimeException{
    private final String msg;
}
