package com.ecommerce.RFQService.Client;

import com.ecommerce.RFQService.Record.CartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="CartService")
public interface CartClient {
    @PostMapping("api/v1/Cart/getCart/{customerId}")
    public CartResponse getCartByCustomerId(@PathVariable String customerId);

    @DeleteMapping("api/v1/Cart/{customerId}")
    public String deleteCart(@PathVariable String customerId);

    }
