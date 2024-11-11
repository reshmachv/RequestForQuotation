package com.ecommerce.RFQService.Respository;

import com.ecommerce.RFQService.Model.QuotedItems;
import com.ecommerce.RFQService.Model.RequestForQuotations;
import com.ecommerce.RFQService.Model.SupplierRfqQuotations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuotedItemRepository extends MongoRepository<QuotedItems,String> {

}

