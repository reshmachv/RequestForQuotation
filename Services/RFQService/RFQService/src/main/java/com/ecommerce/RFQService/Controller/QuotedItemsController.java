package com.ecommerce.RFQService.Controller;

import com.ecommerce.RFQService.Model.QuotedItems;
import com.ecommerce.RFQService.Model.RequestForQuotations;
import com.ecommerce.RFQService.Model.RfqStatus;
import com.ecommerce.RFQService.Service.QuotationItemService;
import com.ecommerce.RFQService.Service.SequenceGeneratorService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/QuotationItem")
public class QuotedItemsController {

    @Autowired
    private QuotationItemService rfqService;

    @PutMapping("/{supplierId}")
    public ResponseEntity<QuotedItems> updateRfqBySupplier(
            @RequestParam (name = "ItemId")String rfqItemId,
            @PathVariable String supplierId,
            @RequestParam (name = "status",required = true)RfqStatus newStatus,
            @RequestParam(name = "comment/reason",required = true) String comment,
            @RequestParam(name="quotedPrice",required = false) Float price)
    {
            return ResponseEntity.ok(rfqService.updateSupplierQuotationStatus(supplierId,rfqItemId,newStatus, comment,price));
    }

    @PutMapping("/customer/{customerId}")
    public ResponseEntity<QuotedItems> updateRfqByCustomer(
            @PathVariable String customerId,
            @RequestParam (name = "RfqId")String rfqId,
            @RequestParam (name = "ItemId")String rfqItemId,
            @RequestParam (name = "supplierId")String supplierId,
            @RequestParam (name = "status")RfqStatus newStatus)
    {
        return ResponseEntity.ok(rfqService.updateRFQItemStatus(customerId,rfqItemId,
                newStatus,supplierId));
    }

}
