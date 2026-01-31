package com.funobile.customerservice;

import com.funobile.customerservice.entity.Customer;
import com.funobile.customerservice.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CustomerService service) {
        return args -> {
            List<String> names = List.of("John", "Jane", "Jack", "Jill");

            names.forEach(name -> service.createCustomer(
                    service.createCustomer(Customer.builder().name(name).email(name + "@email.com").build())
            ));

        };
    }


}
