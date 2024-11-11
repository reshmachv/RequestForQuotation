package com.ecommerce.RFQService.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Setter
@Getter
@Document(collection = "database_sequence")
public class DatabaseSequence {

        @Id
        private String id;

        private long seq;

}
