package com.ecommerce.RFQService.Model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Builder
@Data
public class SupplierRfqQuotations{
        private String supplierName;
        private String companyName;
        private String comment;
        private Float QuotedPrice;
        private String status;
        private LocalDateTime modificationDate;
        private LocalDateTime validTill;
}
