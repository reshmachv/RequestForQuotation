package com.ecommerce.SupplierService.Controller;

import com.ecommerce.SupplierService.Record.CatalogSupplierResponse;
import com.ecommerce.SupplierService.Model.Supplier;
import com.ecommerce.SupplierService.Record.SupplierResponse;
import com.ecommerce.SupplierService.Services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/v1/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping()
    public ResponseEntity<?>createSupplier(@RequestBody Supplier request){
        return ResponseEntity.ok(supplierService.createSupplier(request));
    }

    @GetMapping("/findSupplier/{supplierId}")
    public ResponseEntity<SupplierResponse>searchSupplierById(@PathVariable String supplierId){
        return ResponseEntity.ok(supplierService.searchSupplier(supplierId));
    }

    //for the purpose of getting supplier detail in product service via feign client
    @GetMapping("/{supplierId}")
    public List<SupplierResponse> getSupplierDetail(@PathVariable List<String> supplierId){
        return supplierService.getSupplierDetail(supplierId);
    }



}
