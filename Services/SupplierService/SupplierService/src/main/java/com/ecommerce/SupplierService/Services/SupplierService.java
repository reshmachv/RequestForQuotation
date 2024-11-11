package com.ecommerce.SupplierService.Services;

import com.ecommerce.SupplierService.Exception.SupplierNotFoundException;
import com.ecommerce.SupplierService.Model.Address;
import com.ecommerce.SupplierService.Record.CatalogSupplierResponse;
import com.ecommerce.SupplierService.Model.Supplier;
import com.ecommerce.SupplierService.Model.SupplierMapper;
import com.ecommerce.SupplierService.Record.SupplierResponse;
import com.ecommerce.SupplierService.Repository.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierMapper mapper;


   /* @PersistenceContext
    private EntityManager entityManager;

   public Long getNextSequenceValue(String sequenceName) {
        BigInteger nextValue = (BigInteger) entityManager.createNativeQuery("SELECT NEXT VAL(:sequenceName)")
                .setParameter("sequenceName", sequenceName)
                .getSingleResult();
        return nextValue.longValue();*/


    @Transactional
    public String createSupplier( Supplier supplier) {
        try {
            if (check(supplier.getSupplierEmail())) {
                return "Supplier Already exist!";
            }
            supplierRepository.save(supplier);
            return "Supplier created successfully!";
        }
        catch(Exception e){
            e.printStackTrace();
            return "something went wrong!";
        }
    }
    public Boolean check(String emailId){
        return supplierRepository.existsBysupplierEmail(emailId);
    }

    public SupplierResponse searchSupplier(String supplierId){
        return supplierRepository.findById(supplierId).map(mapper::fromSupplier).orElseThrow(()->
                new SupplierNotFoundException(String.format("no supplier found with this Id")));
    }

    public List<SupplierResponse> getSupplierDetail(List<String> supplierId) {
        //return supplierRepository.findById(supplierId).map(mapper::sendSupplier).orElse(null);
        List<SupplierResponse> supplier=supplierRepository.findAllById(supplierId).stream().map(mapper::fromSupplier).toList();
        return supplier;
    }


}
