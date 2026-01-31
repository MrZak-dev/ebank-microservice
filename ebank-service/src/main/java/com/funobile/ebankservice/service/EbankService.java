package com.funobile.ebankservice.service;

import com.funobile.ebankservice.entity.BankAccount;
import com.funobile.ebankservice.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EbankService {

    private final BankAccountRepository bankAccountRepository;

    public EbankService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }


    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }


    public BankAccount getBankAccountById(String id) {
        return bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank account not found"));
    }


    public List<BankAccount> getBankAccountByCustomerId(Long customerId) {
        return bankAccountRepository.findByCustomerId(customerId);
    }


    public BankAccount createBankAccount(BankAccount bankAccount) {
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(new Date());
        return bankAccountRepository.save(bankAccount);
    }


}
