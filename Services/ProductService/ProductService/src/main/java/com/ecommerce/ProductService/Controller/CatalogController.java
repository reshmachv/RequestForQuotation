package com.ecommerce.ProductService.Controller;

import com.ecommerce.ProductService.Model.Catalog;
import com.ecommerce.ProductService.Record.SupplierResponse;
import com.ecommerce.ProductService.Services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;
    @PostMapping
    public ResponseEntity<String>createCatalog(@RequestBody  Catalog catalog){
        return ResponseEntity.ok(catalogService.createCatalog(catalog));
    }

    @GetMapping("/{msn}")
    public List<SupplierResponse> getSupplierDetails(@PathVariable String msn){
        return catalogService.getSupplierDetail(msn);
    }


}
