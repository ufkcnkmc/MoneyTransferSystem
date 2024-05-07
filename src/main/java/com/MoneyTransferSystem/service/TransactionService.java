package com.MoneyTransferSystem.service;

import com.MoneyTransferSystem.entity.Account;
import com.MoneyTransferSystem.entity.Transaction;
import com.MoneyTransferSystem.exception.InsufficientFundsException;
import com.MoneyTransferSystem.repository.AccountRepository;
import com.MoneyTransferSystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private AccountService accountService;

    public Transaction transferMoney(Long senderAccountId, Long receiverAccountId, Long amount) throws InsufficientFundsException {
        Account senderAccount = accountService.getAccount(senderAccountId);
        Account receiverAccount = accountService.getAccount(receiverAccountId);

        if (senderAccount.getBalance() < amount) {
            throw new InsufficientFundsException("Yetersiz Bakiye");
        }

        senderAccount.setBalance((senderAccount.getBalance() - amount));
        receiverAccount.setBalance((receiverAccount.getBalance() + amount));

        accountService.updateAccountBalance(senderAccount, senderAccount.getBalance());
        accountService.updateAccountBalance(receiverAccount, receiverAccount.getBalance());

        Transaction transaction = new Transaction(senderAccount, receiverAccount, amount, LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

}
