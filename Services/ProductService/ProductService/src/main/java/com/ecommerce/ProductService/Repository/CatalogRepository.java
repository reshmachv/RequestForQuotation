package com.ecommerce.ProductService.Repository;

import com.ecommerce.ProductService.Model.Catalog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CatalogRepository extends MongoRepository<Catalog,String> {

}
