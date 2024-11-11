package com.ecommerce.RFQService.Scheduler;

import com.ecommerce.RFQService.Client.CatalogClient;
import com.ecommerce.RFQService.Model.QuotedItems;
import com.ecommerce.RFQService.Model.RfqStatus;
import com.ecommerce.RFQService.Record.SupplierDetails;
import com.ecommerce.RFQService.Model.SupplierRfqQuotations;
import com.ecommerce.RFQService.Respository.QuotedItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SupplierAssignment {
    @Autowired
    private QuotedItemRepository quotedItemRepository;

    @Autowired
    private CatalogClient catalogClient;

    @Scheduled(cron = "0 */2 * ? * *")
    public void assignSuppliers(){
        List<QuotedItems>filterItemList=quotedItemRepository.findAll().stream().filter(x->"CREATED".equals(x.getRfqItemStatus())&& x.getSuppliersRfqDetails().isEmpty()).collect(Collectors.toList());
        //List<SupplierRfqQuotations>supplierRfqQuotationsList=new ArrayList<>();
        for(QuotedItems items:filterItemList){
            List<SupplierDetails> supplier=catalogClient.getSupplierDetails(items.getItem().getMsn());
            //List<>supplierRfqQuotationsList=new ArrayList<>();
            HashMap<String,SupplierRfqQuotations>map=new HashMap<>();
            for(SupplierDetails supplierDetails:supplier){
                SupplierRfqQuotations supplierRfqQuotations=SupplierRfqQuotations.builder()
                        .supplierName(supplierDetails.Fname())
                        .companyName(supplierDetails.companyName())
                        .modificationDate(LocalDateTime.now())
                        .status(RfqStatus.AWAITING_ACCEPTANCE.toString()).build();
                map.put(supplierDetails.supplierId(),supplierRfqQuotations);
            }
            items.setSuppliersRfqDetails(map);
            items.setRfqItemStatus(RfqStatus.AWAITING_ACCEPTANCE.toString());
            quotedItemRepository.save(items);
        }
    }
}
