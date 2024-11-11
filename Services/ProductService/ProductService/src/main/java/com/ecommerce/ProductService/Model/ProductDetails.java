package com.ecommerce.ProductService.Model;

import com.ecommerce.ProductService.Record.ProductDetail;
import com.ecommerce.ProductService.Record.SupplierResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetails {
    private ProductDetail product;
    private List<SupplierResponse> supplier;
}
