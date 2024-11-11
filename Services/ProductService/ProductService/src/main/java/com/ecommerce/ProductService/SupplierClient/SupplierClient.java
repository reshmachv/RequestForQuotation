package com.ecommerce.ProductService.SupplierClient;

import com.ecommerce.ProductService.Record.SupplierResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("SupplierService")
public interface SupplierClient {
    @GetMapping("api/v1/suppliers/{supplierId}")
    public ResponseEntity<List<SupplierResponse>> getSupplierDetail(@PathVariable List<String> supplierId);
}
