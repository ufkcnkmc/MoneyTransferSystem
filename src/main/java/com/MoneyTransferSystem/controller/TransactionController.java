package com.MoneyTransferSystem.controller;

import com.MoneyTransferSystem.dto.request.TransactionRequest;
import com.MoneyTransferSystem.entity.Transaction;
import com.MoneyTransferSystem.exception.InsufficientFundsException;
import com.MoneyTransferSystem.repository.TransactionRepository;
import com.MoneyTransferSystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> transferMoney(@RequestBody TransactionRequest request) throws InsufficientFundsException {
        Transaction transaction = transactionService.transferMoney(request.getSenderAccountId(), request.getReceiverAccountId(), request.getAmount());
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }

}
