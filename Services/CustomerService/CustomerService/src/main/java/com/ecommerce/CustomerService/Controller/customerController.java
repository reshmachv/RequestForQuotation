package com.ecommerce.CustomerService.Controller;

import com.ecommerce.CustomerService.Model.Customer;
import com.ecommerce.CustomerService.Model.CustomerMapper;
import com.ecommerce.CustomerService.Model.CustomerRequest;
import com.ecommerce.CustomerService.Model.CustomerResponse;
import com.ecommerce.CustomerService.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class customerController {
    @Autowired
    private CustomerService customerService;


    @PostMapping()
    public ResponseEntity<?>createCustomer(@Valid @RequestBody  CustomerRequest request){
       return ResponseEntity.ok(customerService.createCustomer(request));
    }
    @PutMapping()
    public ResponseEntity<?>updateCustomer(@RequestBody CustomerRequest request){
        customerService.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    //to provide customer's details to the cart service.Using in cart service
    @GetMapping("/customerExistence/{customerId}")
    public Boolean findCustomerById(@PathVariable String customerId){
        return customerService.findById(customerId);
    }

    @GetMapping("/findAllCustomers")
    public ResponseEntity<List<CustomerResponse>>findAllCustomers(){
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponse>findCustomerByEmail(@PathVariable String email)
    {
        return ResponseEntity.ok(customerService.findCustomerByEmail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?>deleteCustomerByEmail(@PathVariable String email){
        return ResponseEntity.ok(customerService.deleteCustomerByEmail(email));
    }

    @PostMapping("/getCustomer")
    public CustomerResponse findCustomer(@RequestParam(name="customerId") String customerId){
        return customerService.findCustomer(customerId);
    }

}
