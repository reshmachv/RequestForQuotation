package com.ecommerce.CartService.Controller;


import com.ecommerce.CartService.Model.Cart;
import com.ecommerce.CartService.Record.CartResponse;
import com.ecommerce.CartService.Record.PurchaseRequest;
import com.ecommerce.CartService.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{customerId}")
    public ResponseEntity<Integer> createCart(@RequestBody List<PurchaseRequest> purchaseRequest ,
                                                   @PathVariable String customerId){
        return ResponseEntity.ok(cartService.createCart(purchaseRequest, customerId));
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<CartResponse>getCart(@PathVariable String customerId){
        return ResponseEntity.ok(cartService.getCart(customerId));
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CartResponse>deleteItem(@PathVariable String customerId,
                                                  @RequestParam (name="ProductId") String productId){
        return ResponseEntity.ok(cartService.deleteItem(customerId,productId));
    }

    //to get the cart for the RFQ services
    @PostMapping("/getCart/{customerId}")
    public CartResponse getCartByCustomerId(@PathVariable String customerId){
        return cartService.getCartbyCustomerId(customerId);
    }

    //to delete the cart after RFQ has been created (BY RFQ SERVICE)
    @DeleteMapping("/{customerId}")
    public String deleteCart(@PathVariable String customerId){
        return cartService.deleteItemAfterOrder(customerId);
    }

    @DeleteMapping("/deleteCart/{customerId}")
    public ResponseEntity<CartResponse>deleteCart(@PathVariable String customerId,
                                                  @RequestParam (name="ProductId") String productId){
        return ResponseEntity.ok(cartService.deleteItem(customerId,productId));
    }





}
