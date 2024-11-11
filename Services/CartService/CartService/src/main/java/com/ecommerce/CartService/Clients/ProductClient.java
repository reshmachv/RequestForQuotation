package com.ecommerce.CartService.Clients;

import com.ecommerce.CartService.Record.PurchaseRequest;
import com.ecommerce.CartService.Record.ProductQuantityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("ProductService")
public interface ProductClient {
    @PostMapping("api/v1/Products/purchase")
    public List<ProductQuantityDTO> purchaseProducts(
            @RequestBody List<PurchaseRequest> request
    );
}
