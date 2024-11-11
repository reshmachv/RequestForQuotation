package com.ecommerce.ProductService.Services;

import com.ecommerce.ProductService.Model.Catalog;
import com.ecommerce.ProductService.Record.SupplierResponse;
import com.ecommerce.ProductService.Repository.CatalogRepository;
import com.ecommerce.ProductService.SupplierClient.SupplierClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CatalogService {
    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private SupplierClient supplierClient;

    public Catalog findMsn(String msn) {
        return catalogRepository.findById(msn).orElse(null);
    }
    public String createCatalog(Catalog catalog) {
            String msn="MSN"+ UUID.randomUUID().toString().replace("-","A").substring(0,12);
            catalog.setMsn(msn);
            //System.out.println(catalog);
            Catalog current=findMsn(msn);
            if(current==null) {
                try {
                    catalogRepository.save(catalog);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                return "MSN already Exist!";
            }
            return "Successfully Created!"+msn ;
        }

    public List<SupplierResponse> getSupplierDetail(String msn) {
        List<String> supplierId=findMsn(msn).getSupplierId();
        return supplierClient.getSupplierDetail(supplierId).getBody();
    }
}
