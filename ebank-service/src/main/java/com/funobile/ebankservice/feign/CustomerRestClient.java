package com.funobile.ebankservice.feign;

import com.funobile.ebankservice.entity.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerRestClient {
    @GetMapping("/customers/{id}")
    @CircuitBreaker(fallbackMethod = "getCustomerByIdFallback", name = "customerServiceCircuitBreaker")
    Customer getCustomerById(@PathVariable Long id);


    default Customer getCustomerByIdFallback(Long id, Exception e) {
        return new Customer(id, "Unknown", "Unknown");
    }
}
