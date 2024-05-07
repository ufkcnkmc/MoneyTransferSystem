package com.MoneyTransferSystem.controller;

import com.MoneyTransferSystem.dto.request.AccountCreateRequest;
import com.MoneyTransferSystem.entity.Account;
import com.MoneyTransferSystem.entity.Customer;
import com.MoneyTransferSystem.exception.CustomerNotFoundException;
import com.MoneyTransferSystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Customer customer, @RequestParam Long balance) {
        Account createdAccount = accountService.createAccount(customer, balance);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @PostMapping("/account")
    public ResponseEntity<Account> createAccountByCustomerId(@RequestBody AccountCreateRequest accountCreateRequest) {
        try {
            Account createdAccount = accountService.createAccountByCustomerId(accountCreateRequest.accountId(), accountCreateRequest.balance());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        Account account = accountService.getAccount(id);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccountBalance(@PathVariable Long id, @RequestParam Long amount) {
        Account account = accountService.getAccount(id);
        if (account != null) {
            accountService.updateAccountBalance(account, amount);
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
