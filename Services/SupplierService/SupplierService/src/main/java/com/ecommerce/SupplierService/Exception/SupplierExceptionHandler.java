package com.ecommerce.SupplierService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SupplierExceptionHandler {
    @ExceptionHandler(SupplierNotFoundException.class)
    public ResponseEntity<String> handle(SupplierNotFoundException exp) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exp.getMsg());
    }
}
