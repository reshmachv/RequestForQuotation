package com.ecommerce.RFQService.Model;

import com.ecommerce.RFQService.Record.CustomerDetails;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class RequestForQuotations {
    @Transient
    public static final String SEQUENCE_NAME = "quotation_sequence";
    @Id
    private String id;
    private CustomerDetails customer;
    @DBRef
    private List<QuotedItems> quotedItems;
    private LocalDateTime creationDate;
    private float totalPrice;
    private float newPrice;
    private String Status;
}
