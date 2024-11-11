package com.ecommerce.CartService.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({CustomerNotFoundException.class})
    public ResponseEntity<String> handle(CustomerNotFoundException exp) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exp.getMsg());
    }

    @ExceptionHandler({EmptyCartException.class})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> handle(EmptyCartException exp) {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(exp.getMsg());
    }
}
