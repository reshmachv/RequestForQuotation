package com.ecommerce.RFQService.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Document
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Setter
public class QuotedItems {
    @Transient
    public static final String SEQUENCE_NAME = "RfqItems_sequence";
    @Id
    private String quoteId;
    private CartItem item;
    private String RfqItemStatus;
    private String comment;
    private HashMap<String,SupplierRfqQuotations> suppliersRfqDetails=new HashMap<>();
    private float newQuotePrice;
}
