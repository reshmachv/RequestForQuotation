package com.ecommerce.CustomerService.Service;

import com.ecommerce.CustomerService.Exceptions.CustomerNotFoundException;
import com.ecommerce.CustomerService.Model.Customer;
import com.ecommerce.CustomerService.Model.CustomerMapper;
import com.ecommerce.CustomerService.Model.CustomerRequest;
import com.ecommerce.CustomerService.Model.CustomerResponse;
import com.ecommerce.CustomerService.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public CustomerResponse findCustomer(String customerId){
            Customer customer = customerRepository.findByCustomerId(customerId).orElseThrow(
                    ()->new CustomerNotFoundException("Customer don't exist!"));
            return customerMapper.fromCustomer(customer);
    }
    public ResponseEntity<String> createCustomer(CustomerRequest request) {
        if(existCustomer(request.email())!=null){
            return new ResponseEntity<>("Customer already exist!", HttpStatus.OK);
        }
        Customer customer=customerMapper.toCustomer(request);
        customerRepository.save(customer);
        return new ResponseEntity<>("User created successfully!",HttpStatus.OK);
    }

    public Customer existCustomer(String emailId){
        return customerRepository.findByEmail(emailId);
    }

    public void updateCustomer(CustomerRequest request) {
       Customer customer=customerRepository.findByEmail(request.email());
       if(StringUtils.hasLength(request.Fname())){
           customer.setFname(request.Fname());
       }
        if(StringUtils.hasLength(request.Lname())){
            customer.setFname(request.Lname());
        }
        if(StringUtils.hasLength(request.email())){
            customer.setFname(request.email());
        }
        if(StringUtils.hasLength(request.password())){
            customer.setFname(request.password());
        }
        if(request.Address()!=null){
            customer.setAddress(request.Address());
        }
        customerRepository.save(customer);
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll().stream().
                map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }


    public CustomerResponse findCustomerByEmail(String email) {
        return customerMapper.fromCustomer(customerRepository.findByEmail(email));
    }


    public ResponseEntity<?> deleteCustomerByEmail(String email) {
        customerRepository.deleteByEmail(email);
        return ResponseEntity.accepted().build();
    }

    public Boolean findById(String customerId) {
        customerRepository.findByCustomerId(customerId);
      Customer customer=customerRepository.findByCustomerId(customerId).orElse(null);
      if(customer==null){
          return false;
      }
      return true;
    }

}
