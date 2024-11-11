package com.ecommerce.RFQService.Controller;

import com.ecommerce.RFQService.Model.RequestForQuotations;
import com.ecommerce.RFQService.Record.CustomerDetails;
import com.ecommerce.RFQService.Service.QuotationService;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Quotation")
public class QuotationController {

    @Autowired
    private QuotationService quotationService;

    @PostMapping("/{customerId}")
    //create RFQ order
    public ResponseEntity<RequestForQuotations>createQuotation(@PathVariable String customerId){
        return ResponseEntity.ok(quotationService.createQuotation(customerId));
    }
    //list out all the rfq created by customer
    @GetMapping("/{customerId}")
    public ResponseEntity<List<RequestForQuotations>>showAllRfqToCustomer(@PathVariable String customerId){
        return ResponseEntity.ok(quotationService.showAllRfqToCustomer(customerId));
    }
    //list out the RFQs with the status filter


    //



}
