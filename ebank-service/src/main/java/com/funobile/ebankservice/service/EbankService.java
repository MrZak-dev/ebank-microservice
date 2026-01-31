package com.funobile.ebankservice.service;

import com.funobile.ebankservice.entity.BankAccount;
import com.funobile.ebankservice.entity.Customer;
import com.funobile.ebankservice.feign.CustomerRestClient;
import com.funobile.ebankservice.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EbankService {

    private final BankAccountRepository bankAccountRepository;
    private final CustomerRestClient customerRestClient;

    public EbankService(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }


    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }


    public BankAccount getBankAccountById(String id) {
        BankAccount bankAccount = bankAccountRepository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Bank account not found")
                );
        bankAccount.setCustomer(customerRestClient.getCustomerById(bankAccount.getCustomerId()));

        return bankAccount;

    }


    public List<BankAccount> getBankAccountByCustomerId(Long customerId) {
        return bankAccountRepository.findByCustomerId(customerId);
    }


    public BankAccount createBankAccount(BankAccount bankAccount) {
        try {
            Customer customer = customerRestClient.getCustomerById(bankAccount.getCustomerId());
            bankAccount.setId(UUID.randomUUID().toString());
            bankAccount.setCreatedAt(new Date());
            bankAccount.setCustomerId(customer.getId());
            return bankAccountRepository.save(bankAccount);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
