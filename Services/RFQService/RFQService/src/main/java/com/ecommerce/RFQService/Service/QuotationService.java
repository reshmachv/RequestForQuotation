package com.ecommerce.RFQService.Service;

import com.ecommerce.RFQService.Client.CartClient;
import com.ecommerce.RFQService.Client.CatalogClient;
import com.ecommerce.RFQService.Client.CustomerClient;
import com.ecommerce.RFQService.Model.QuotedItems;
import com.ecommerce.RFQService.Model.RequestForQuotations;
import com.ecommerce.RFQService.Model.CartItem;
import com.ecommerce.RFQService.Model.RfqStatus;
import com.ecommerce.RFQService.Record.CartResponse;
import com.ecommerce.RFQService.Record.CustomerDetails;
import com.ecommerce.RFQService.Record.SupplierDetails;
import com.ecommerce.RFQService.Respository.QuotationRepository;
import com.ecommerce.RFQService.Respository.QuotedItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class QuotationService {

    @Autowired
    private QuotationRepository quotationRepository;

    @Autowired
    private CartClient cartClient;

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private CatalogClient catalogClient;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private QuotedItemRepository quotedItemRepository;


    @Transactional
    public RequestForQuotations createQuotation(String customerId) {
            CustomerDetails customerDetails = customerClient.findCustomer(customerId);
            CartResponse cartRequest = cartClient.getCartByCustomerId(customerId);
            HashMap<String, CartItem> cartItems = cartRequest.productList();
            float totalPrice = cartRequest.totalPrice();
            Long RfqId=sequenceGeneratorService.generateSequence(RequestForQuotations.SEQUENCE_NAME);
            Long RfqItemId=sequenceGeneratorService.generateSequence(QuotedItems.SEQUENCE_NAME);
            List<QuotedItems> quotedItemsList = new ArrayList<>();
            for (Map.Entry<String, CartItem> entry : cartItems.entrySet()) {
                String productId = entry.getKey(); // Get the productId
                CartItem product = entry.getValue();
                List<SupplierDetails> supplier=catalogClient.getSupplierDetails(product.getMsn());
                // Create a new instance for each quoted item
                QuotedItems quotedItem = QuotedItems.builder()
                        .quoteId(sequenceGeneratorService.generateQuoteId())
                        .item(product)
                        .newQuotePrice(totalPrice)
                        .RfqItemStatus(RfqStatus.CREATED.toString())
                        .build();
                quotedItemsList.add(quotedItem);
            }
            RequestForQuotations quotations=RequestForQuotations.builder().id("RFQ"+String.format("%05d",RfqId))
                    .customer(customerDetails).quotedItems(quotedItemsList)
                    .Status(RfqStatus.CREATED.toString()).creationDate(LocalDateTime.now())
                    .newPrice(totalPrice).totalPrice(totalPrice)
                    .build();
            // Save the quotations first
            RequestForQuotations savedQuotations = quotationRepository.save(quotations);

            // Only save quoted items if quotations are saved successfully
        for (QuotedItems quotedItem : quotedItemsList) {
            quotedItemRepository.save(quotedItem);
        }
        cartClient.deleteCart(customerId);
        return savedQuotations;
    }
    public List<RequestForQuotations> showAllRfqToCustomer(String customerId){
        List<RequestForQuotations> presentQuotations = quotationRepository.findByCustomer_CustomerId(customerId);
        if(presentQuotations==null){
            return ResponseEntity.status(404).body(presentQuotations).getBody();
        }
        return presentQuotations;
    }

    public List<RequestForQuotations>statusFilterRfqs(String customerId,String status){
        List<RequestForQuotations>rfqWithFilter=quotationRepository.findByCustomer_CustomerIdAndStatus(customerId,status);
        if(rfqWithFilter==null){
            return ResponseEntity.status(404).body(rfqWithFilter).getBody();
        }
        return rfqWithFilter;
    }
    }