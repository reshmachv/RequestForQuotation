package com.ecommerce.RFQService.Respository;

import com.ecommerce.RFQService.Model.RequestForQuotations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface QuotationRepository extends MongoRepository<RequestForQuotations,String> {
    List<RequestForQuotations>findByCustomer_CustomerId(String customerId);
    List<RequestForQuotations> findByCustomer_CustomerIdAndStatus(String customerId, String status);
    //Boolean findByIdAndCustomer_CustomerId(String id,String customerId);
    @Query("{'customer.customerId' : ?0, 'quotedItems.quoteId' : ?1}")
    Optional<RequestForQuotations> findByCustomerIdAndQuoteId(String customerId, String rfqItemId);

}
