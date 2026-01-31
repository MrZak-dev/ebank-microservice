package com.funobile.ebankservice.controller;

import com.funobile.ebankservice.entity.BankAccount;
import com.funobile.ebankservice.service.EbankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EbankController {

    private final EbankService ebankService;

    public EbankController(EbankService ebankService) {
        this.ebankService = ebankService;
    }


    @GetMapping("/accounts")
    public List<BankAccount> getAllBankAccounts() {
        return ebankService.getAllBankAccounts();
    }


    @GetMapping("/accounts/{id}")
    public BankAccount getBankAccountById(@PathVariable String id) {
        return ebankService.getBankAccountById(id);
    }


    @GetMapping("/accounts/customer/{customerId}")
    public List<BankAccount> getBankAccountByCustomerId(@PathVariable Long customerId) {
        return ebankService.getBankAccountByCustomerId(customerId);
    }


    @PostMapping("/accounts")
    public BankAccount createBankAccount(@RequestBody BankAccount bankAccount) {
        return ebankService.createBankAccount(bankAccount);
    }


}
