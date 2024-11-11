package com.ecommerce.ProductService.Record;



public record SupplierResponse(
        String supplierId,
        String Fname,
        String Lname,
        String companyName,
        String supplierEmailId,
        String area,
        String companyId,
        String state
) {
}
