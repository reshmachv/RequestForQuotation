package com.ecommerce.ProductService.Services;

import com.ecommerce.ProductService.Exceptions.MsnNotFoundException;
import com.ecommerce.ProductService.Exceptions.ProductNotFoundException;
import com.ecommerce.ProductService.Exceptions.ProductPurchaseException;
import com.ecommerce.ProductService.Model.*;
import com.ecommerce.ProductService.Record.*;
import com.ecommerce.ProductService.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private ProductMapper mapper;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;


    public ResponseEntity<String> createProduct(ProductRequest request) {
        Long id=sequenceGeneratorService.generateSequence(Product.SEQUENCE_NAME);
        Product newProduct=mapper.toProduct(request);
        newProduct.setProductId("PID"+String.format("%05d", id));
        try {
            if(catalogService.findMsn(request.msn())==null){
                throw new MsnNotFoundException("Msn is not present in catalog Please provide the valid msn for product creation!");
            }
            productRepository.save(newProduct);
            return ResponseEntity.ok("Product Created Successfully!");
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("Something went wrong!");
        }
    }

    public ResponseEntity<ProductDetails> getProductDetails(String Id) {
        ProductDetail detail=productRepository.findById(Id).map(mapper::toProductDetail)
                .orElseThrow(()->new ProductNotFoundException("no product found with this productId!"));
        List<SupplierResponse> supplierDetail=catalogService.getSupplierDetail(detail.msn());
        return ResponseEntity.ok(new ProductDetails(detail,supplierDetail));
    }

    public ProductResponse showProduct(String supplierId) {
        return productRepository.findById(supplierId).map(mapper::fromProduct)
                .orElseThrow(()->new ProductNotFoundException("no product found with this productId!"));
    }

        //for the list of products

        public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> request) {
        var productIds = request
                .stream()
                .map(PurchaseRequest::productId)
                .toList();
        var storedProducts = productRepository.findAllByProductIdInOrderByProductId(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exist");
        }
        var sortedRequest=request.stream().sorted(Comparator.comparing(PurchaseRequest::productId)).toList();
        List<PurchaseResponse>purchaseProducts=new ArrayList<>();
        for(int i=0;i<storedProducts.size();i++){
            purchaseProducts.add(mapper.toPurchaseResponse(storedProducts.get(i),sortedRequest.get(i).quantity()));
        }
        return purchaseProducts;
    }

/*    public PurchaseResponse purchaseProducts(PurchaseRequest request){
        Product productInfo=productRepository.findById(request.productId()).orElseThrow(()->new ProductNotFoundException("no product found with this productId!"));
        return mapper.toPurchaseResponse(productInfo,request.quantity());
    }*/
}
