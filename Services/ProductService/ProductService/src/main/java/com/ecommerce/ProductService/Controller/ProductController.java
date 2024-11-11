package com.ecommerce.ProductService.Controller;

import com.ecommerce.ProductService.Model.ProductDetails;
import com.ecommerce.ProductService.Record.ProductRequest;
import com.ecommerce.ProductService.Record.ProductResponse;
import com.ecommerce.ProductService.Record.PurchaseRequest;
import com.ecommerce.ProductService.Record.PurchaseResponse;
import com.ecommerce.ProductService.Services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<String>createProduct(@RequestBody @Valid ProductRequest product){
        return productService.createProduct(product);
    }

    //for the supplier client to get complete detail of the product.
    @GetMapping("/productDetail")
    public ResponseEntity<ProductDetails>getproductDetails(@RequestParam(name="productId")  String Id){
        return productService.getProductDetails(Id);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse>showProduct(@PathVariable String productId){
        return ResponseEntity.ok(productService.showProduct(productId));
    }

    //to get the products detail for the cart
    @PostMapping("/purchase")
    public List<PurchaseResponse> purchaseProducts(
            @RequestBody List<PurchaseRequest> request
    ) {
        return productService.purchaseProducts(request);
    }

}
