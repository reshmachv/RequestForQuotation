package com.ecommerce.CartService.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("CustomerService")
public interface CustomerClient {

    @GetMapping("api/v1/customers/{customerId}")
    public Boolean findCustomerById(@RequestBody String customerId);
}
