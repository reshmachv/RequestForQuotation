package com.ecommerce.RFQService.Service;

import com.ecommerce.RFQService.Model.QuotedItems;
import com.ecommerce.RFQService.Model.RfqStatus;
import com.ecommerce.RFQService.Model.SupplierRfqQuotations;
import com.ecommerce.RFQService.Respository.QuotationRepository;
import com.ecommerce.RFQService.Respository.QuotedItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class QuotationItemService {

    @Autowired
    private QuotedItemRepository quotationItemRepository;

    @Autowired
    private QuotationRepository quotationRepository;

    public boolean isStatusChangeAllowed(String currentStatus, String newStatus) {
        return switch (currentStatus) {
            case "QUOTED" -> newStatus.equals("ACCEPTED") ||
                    newStatus.equals("CLARIFICATION_REQUIRED") ||
                    newStatus.equals("REJECTED") ||
                    newStatus.equals("IN_REVIEW");
            case "IN_REVIEW" -> newStatus.equals("ACCEPTED") ||
                    newStatus.equals("CLARIFICATION_REQUIRED") ||
                    newStatus.equals("REJECTED") ||
                    newStatus.equals("CANCELLED");
            case "AWAITING_ACCEPTANCE" ->
                    newStatus.equals("CANCELLED");
            case "ACCEPTED", "SUPPLIER_REJECTED", "CANCELLED","CLOSED" -> false;  // No status change allowed

            default -> false;
        };
    }
    public boolean isSupplierStatusChangeAllowed(String currentStatus, String newStatus) {
        // Similar validation for supplier quotation status
        return switch (currentStatus) {
            case "CLARIFICATION_REQUIRED", "AWAITING_ACCEPTANCE","QUOTED" ->
                    newStatus.equals("QUOTED") || newStatus.equals("SUPPLIER_REJECTED");
            default -> false;
        };
    }

    public QuotedItems updateRFQItemStatus(String customerId,String rfqItemId,RfqStatus newStatus,String supplierId) {
        if(quotationRepository.findByCustomerIdAndQuoteId(customerId,rfqItemId).isEmpty()){
            throw new RuntimeException("customer is unauthorised to change or update the RFQ.");
        }
        QuotedItems currentQuotedItem=quotationItemRepository.findById(rfqItemId).orElseThrow(()->new RuntimeException("No Item found with this rfqItemId!"));
        HashMap<String, SupplierRfqQuotations>quotationsHashMap=currentQuotedItem.getSuppliersRfqDetails();
        if(!quotationsHashMap.containsKey(supplierId)){
            throw new RuntimeException("no supplier present with this supplierId");
        }
        SupplierRfqQuotations supplierRfqQuotations=quotationsHashMap.get(supplierId);
        if(!isStatusChangeAllowed(supplierRfqQuotations.getStatus(),newStatus.toString())) {
            throw new RuntimeException(" status can't not be updated to "+newStatus);
        }
        supplierRfqQuotations.setStatus(newStatus.toString());
        quotationsHashMap.put(supplierId,supplierRfqQuotations);
        currentQuotedItem.setSuppliersRfqDetails(quotationsHashMap);
        return quotationItemRepository.save(currentQuotedItem);
        }

    public QuotedItems updateSupplierQuotationStatus(String supplierId,String rfqItemId,RfqStatus newStatus,String comment,Float price) {
        QuotedItems currentQuotedItem=quotationItemRepository.findById(rfqItemId).orElseThrow(()->new RuntimeException("No RFQItem found with this Id!"));
        HashMap<String, SupplierRfqQuotations>quotationsHashMap=currentQuotedItem.getSuppliersRfqDetails();
        if(!quotationsHashMap.containsKey(supplierId)){
            throw new RuntimeException("no supplier present with this supplierId");
        }
        SupplierRfqQuotations supplierRfqQuotations=quotationsHashMap.get(supplierId);
        if(!isSupplierStatusChangeAllowed(supplierRfqQuotations.getStatus(),newStatus.toString())) {
            throw new RuntimeException("Supplier is not authorized to use this status "+newStatus);
        }
        supplierRfqQuotations.setStatus(newStatus.toString());
        supplierRfqQuotations.setComment(comment);
        supplierRfqQuotations.setModificationDate(LocalDateTime.now());
        supplierRfqQuotations.setQuotedPrice(price);
        supplierRfqQuotations.setValidTill(LocalDateTime.now().plusDays(10));
        if (newStatus == RfqStatus.SUPPLIER_REJECTED) {
            supplierRfqQuotations.setQuotedPrice(00.0f);
            supplierRfqQuotations.setValidTill(LocalDateTime.now().minusDays(1));
        }
        currentQuotedItem.setSuppliersRfqDetails(quotationsHashMap);
        return quotationItemRepository.save(currentQuotedItem);
        }



    }
