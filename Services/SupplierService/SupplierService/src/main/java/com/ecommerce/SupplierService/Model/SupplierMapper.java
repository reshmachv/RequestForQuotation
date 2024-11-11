package com.ecommerce.SupplierService.Model;

import com.ecommerce.SupplierService.Record.CatalogSupplierResponse;
import com.ecommerce.SupplierService.Record.SupplierResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SupplierMapper {
    public SupplierResponse fromSupplier(Supplier supplier){
        return new SupplierResponse(supplier.getSupplierId(),supplier.getFname(),supplier.getLname()
                ,supplier.getSupplierAddress().getCompanyName(), supplier.getSupplierEmail()
                ,supplier.getSupplierAddress().getArea(),supplier.getBusinessId(),supplier.getSupplierAddress().getState());
    }

    public CatalogSupplierResponse sendSupplier(Supplier supplier){
        return new CatalogSupplierResponse(
                supplier.getFname(),supplier.getLname(),supplier.getSupplierAddress().getCompanyName(),
                supplier.getSupplierAddress().getArea(),supplier.getSupplierAddress().getState()
        );
    }
}
