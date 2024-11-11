package com.ecommerce.CartService.Service;

import com.ecommerce.CartService.Clients.CustomerClient;
import com.ecommerce.CartService.Clients.ProductClient;
import com.ecommerce.CartService.Exceptions.CustomerNotFoundException;
import com.ecommerce.CartService.Exceptions.EmptyCartException;
import com.ecommerce.CartService.Model.Cart;
import com.ecommerce.CartService.Model.CartItems;
import com.ecommerce.CartService.Model.CartMapper;
import com.ecommerce.CartService.Record.*;
import com.ecommerce.CartService.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private CartMapper mapper;

    @Autowired
    private ProductClient productClient;

    //create Cart API
    public Integer createCart(List<PurchaseRequest> productIdQuantityList, String customerId) {
        List<ProductQuantityDTO> purchaseResponses = productClient.purchaseProducts(productIdQuantityList);
            HashMap<String,CartItems>items=new HashMap<>();
            float total_price = 0.00f;
            for (ProductQuantityDTO i : purchaseResponses ) {
                if (i.quantity() > 0) {
                    items.put(i.productId(), new CartItems(i.name(), i.description(),i.msn(), i.price(), i.quantity()));
                    total_price += i.price() * i.quantity();
                }
            }
            cartRepository.save(mapper.toCart(items, total_price, customerId));
            return items.size();
    }

    //Get the cart detail api
    public CartResponse getCart(String customerId) {
        return cartRepository.findById(customerId).map(mapper::toCartResponse).orElseThrow(()->new EmptyCartException("Cart is Empty!"));
    }

    //Delete the item from the cart API
    public CartResponse deleteItem(String customerId,String productId){
        Cart currentCart=cartRepository.findById(customerId).orElseThrow(()->new EmptyCartException("cart is Empty!"));
        currentCart.setTotal((float) (currentCart.getTotal()-(currentCart.getCartItems().get(productId).getPrice()*
                        currentCart.getCartItems().get(productId).getQuantity())));
        currentCart.getCartItems().remove(productId);
        if(currentCart.getCartItems().size()==0){
            cartRepository.delete(currentCart);
            throw new EmptyCartException("Cart got Empty!");
        }
        else {
            cartRepository.save(currentCart);
        }
        return mapper.toCartResponse(currentCart);
    }

    //for RFQ service
    public CartResponse getCartbyCustomerId(String customerId) {
        return cartRepository.findById(customerId).map(mapper::toCartResponse).orElseThrow(()->new EmptyCartException("There is no Item in the cart by this user!"));
    }

    public String deleteItemAfterOrder(String customerId) {
        Cart currentCart=cartRepository.findById(customerId).orElseThrow(()->new EmptyCartException("cart is Empty!"));
        cartRepository.deleteById(customerId);
        return "cart deleted successfully";
    }
}
