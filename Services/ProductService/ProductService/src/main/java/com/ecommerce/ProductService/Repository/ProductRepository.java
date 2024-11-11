package com.ecommerce.ProductService.Repository;

import com.ecommerce.ProductService.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product,String> {
    List<Product> findAllByProductIdInOrderByProductId(List<String> productIds);
}
