package com.ecommerce.CustomerService.Repository;

import com.ecommerce.CustomerService.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer  findByEmail(String emailId);

    void deleteByEmail(String email);

    @Query(value = "SELECT * FROM customer u WHERE u.customer_id = :customerId", nativeQuery = true)
    Optional<Customer> findByCustomerId(String customerId);

}
