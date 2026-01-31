package com.funobile.ebankservice;

import com.funobile.ebankservice.entity.BankAccount;
import com.funobile.ebankservice.service.EbankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class EbankServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(EbankService service) {
        return args -> {
            List<Long> names = List.of(1L, 2L, 3L, 4L);

            names.forEach(customerId -> service.createBankAccount(
                    BankAccount.builder()
                            .type(Math.random() > .5 ? "SAVINGS" : "CURRENT")
                            .balance(new Random().nextDouble() * 10000)
                            .customerId(customerId)
                            .build()
            ));

        };
    }
}
