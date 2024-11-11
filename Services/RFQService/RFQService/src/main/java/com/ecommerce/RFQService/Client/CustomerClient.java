package com.ecommerce.RFQService.Client;

//import com.ecommerce.RFQService.Config.FeignClientConfig;
import com.ecommerce.RFQService.Record.CustomerDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name="CustomerService")
public interface CustomerClient {
    @PostMapping("api/v1/customers/getCustomer")
    public CustomerDetails findCustomer(@RequestParam(name="customerId") String customerId);

}



