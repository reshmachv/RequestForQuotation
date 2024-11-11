package com.ecommerce.CustomerService.Model;

import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request){
        if(request==null){
            return null;
        }
        return Customer.builder().customerId(request.customerId())
                .Fname(request.Fname())
                .Lname(request.Lname())
                .Address(request.Address())
                .email(request.email())
                .password(request.password())
        .build();
    }

    public CustomerResponse fromCustomer(Customer customer)
    {
        if(customer==null){
            return null;
        }
        return new CustomerResponse(customer.getCustomerId(),customer.getFname(),
                customer.getLname(),customer.getEmail(),customer.getAddress().getCompanyName(),customer.getAddress().getPhoneNumber(),
                customer.getAddress().getArea(),customer.getAddress().getLandMark(),customer.getAddress().getPinCode(),
                customer.getAddress().getState());
    }

}
