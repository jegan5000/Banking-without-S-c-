package com.ust.transaction_service.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ust.transaction_service.dto.AccountDTO;
import com.ust.transaction_service.exception.InsufficientBalanceException;
import com.ust.transaction_service.model.Transaction;
import com.ust.transaction_service.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WebClient webClient;

    private static final String ACCOUNT_SERVICE_URL = "http://localhost:8081/api/accounts";

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    public Transaction createTransaction(Transaction transaction) {
        // Fetch the account from Account Service
        AccountDTO account = webClient.get()
                .uri(ACCOUNT_SERVICE_URL + "/" + transaction.getAccountId())
                .retrieve()
                .bodyToMono(AccountDTO.class)
                .block();

        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }

        // Validate balance for DEBIT transactions
        if (transaction.getType().equals("DEBIT") && account.getBalance().compareTo(transaction.getAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance for the transaction.");
        }

        // Update the account balance
        BigDecimal newBalance = transaction.getType().equals("DEBIT")
                ? account.getBalance().subtract(transaction.getAmount())
                : account.getBalance().add(transaction.getAmount());

        webClient.put()
                .uri(ACCOUNT_SERVICE_URL + "/" + transaction.getAccountId() + "/balance")
                .bodyValue(newBalance)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        // Save the transaction
        return transactionRepository.save(transaction);
    }
}


