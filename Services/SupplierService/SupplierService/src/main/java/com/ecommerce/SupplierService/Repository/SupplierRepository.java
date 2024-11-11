package com.ecommerce.SupplierService.Repository;

import com.ecommerce.SupplierService.Model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier,String> {
    boolean existsBysupplierEmail(String supplierEmail);
}
