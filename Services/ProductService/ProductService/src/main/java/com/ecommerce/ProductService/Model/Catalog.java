package com.ecommerce.ProductService.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@Builder
@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Catalog {
    @Id
    private String msn;
    @NotNull
    @NotBlank
    private String category;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @NotEmpty
    private List<String> supplierId;
}
