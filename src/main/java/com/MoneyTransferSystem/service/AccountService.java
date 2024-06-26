package com.MoneyTransferSystem.service;

import com.MoneyTransferSystem.entity.Account;
import com.MoneyTransferSystem.entity.Customer;
import com.MoneyTransferSystem.exception.CustomerNotFoundException;
import com.MoneyTransferSystem.repository.AccountRepository;
import com.MoneyTransferSystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;
    public Account createAccount(Customer customer, Long balance) {
        Account account = new Account(customer, balance);
        accountRepository.save(account);
        return account;
    }
    public Account createAccountByCustomerId(Long customerId, Long balance) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        Account account = new Account(customer, balance);
        accountRepository.save(account);
        return account;
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public void updateAccountBalance(Account account, Long amount) {
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

}
