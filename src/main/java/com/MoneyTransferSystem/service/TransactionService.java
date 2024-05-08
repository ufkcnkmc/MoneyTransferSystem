package com.MoneyTransferSystem.service;

import com.MoneyTransferSystem.entity.Account;
import com.MoneyTransferSystem.entity.Transaction;
import com.MoneyTransferSystem.exception.InsufficientFundsException;
import com.MoneyTransferSystem.repository.AccountRepository;
import com.MoneyTransferSystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private AccountService accountService;
    @Async
    public Transaction transferMoney(Long senderAccountId, Long receiverAccountId, Long amount) throws InsufficientFundsException {
        Account senderAccount = accountService.getAccount(senderAccountId);
        Account receiverAccount = accountService.getAccount(receiverAccountId);

        if (senderAccount.getBalance() < amount) {
            throw new InsufficientFundsException("Yetersiz Bakiye");
        }
        senderAccount.setBalance(senderAccount.getBalance() - amount);
        receiverAccount.setBalance(receiverAccount.getBalance() + amount);

        Transaction transaction = new Transaction(senderAccount, receiverAccount, amount, LocalDateTime.now());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return transactionRepository.save(transaction);
    }
    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId).orElse(null);
    }


}
