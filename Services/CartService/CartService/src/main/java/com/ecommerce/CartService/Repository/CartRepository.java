package com.ecommerce.CartService.Repository;

import com.ecommerce.CartService.Model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



public interface CartRepository extends MongoRepository<Cart, String> {
}
