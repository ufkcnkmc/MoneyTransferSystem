package com.MoneyTransferSystem.controller;

import com.MoneyTransferSystem.dto.request.TransactionRequest;
import com.MoneyTransferSystem.entity.Transaction;
import com.MoneyTransferSystem.exception.InsufficientFundsException;
import com.MoneyTransferSystem.repository.TransactionRepository;
import com.MoneyTransferSystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionByTransactionId(@PathVariable Long transactionId) {
        Transaction transaction = transactionService.getTransactionById(transactionId);
        if (transaction == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transaction);
    }

}
