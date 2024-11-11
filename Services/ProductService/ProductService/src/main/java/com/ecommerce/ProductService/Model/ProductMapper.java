package com.ecommerce.ProductService.Model;

import com.ecommerce.ProductService.Record.*;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse fromProduct(Product product){
        if(product==null){
            return null;
        }
        return new ProductResponse(product.getProductId(),
                product.getProductName(),
                product.getPrice(),
                product.getCatalog().getMsn()
        );
    }

    public Product toProduct(ProductRequest request) {
        if(request==null){
            return null;
        }
        return Product.builder().productId(request.productId())
                .price(request.price())
                .productName(request.productName())
                .catalog(
                        Catalog.builder().msn(request.msn()).build()
                )
        .build();
    }

    public ProductDetail toProductDetail(Product product){
        if(product==null){
            return null;
        }
        return new ProductDetail(product.getProductName(), product.getPrice(),
                product.getCatalog().getMsn(),product.getCatalog().getCategory(),
                product.getCatalog().getDescription());
    }

    public PurchaseResponse toPurchaseResponse(Product product,double quantity){
        return new PurchaseResponse(product.getProductId(), product.getProductName(),
                product.getCatalog().getDescription(),
                product.getCatalog().getMsn(),product.getPrice(), quantity
        );
    }
}
