package com.ecommerce.RFQService.Client;

import com.ecommerce.RFQService.Record.SupplierDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="ProductService")
public interface CatalogClient {

    @GetMapping("api/v1/Catalog/{msn}")
    public List<SupplierDetails> getSupplierDetails(@PathVariable String msn);
}
