package com.ecommerce.CustomerService.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandleException {
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseEntity<String>handle(CustomerNotFoundException exp){
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(exp.getMsg());
    }


}
