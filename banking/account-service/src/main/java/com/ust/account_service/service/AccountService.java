package com.ust.account_service.service;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.account_service.exception.AccountNotFoundException;
import com.ust.account_service.model.Account;
import com.ust.account_service.repository.AccountRepository;
    
    @Service
    public class AccountService {
    
        @Autowired
        private AccountRepository accountRepository;
    
        public List<Account> getAllAccounts() {
            return accountRepository.findAll();
        }
    
       public Account getAccountById(Long id) {
           return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
       }
    
        public Account createAccount(Account account) {
            return accountRepository.save(account);
        }
    
    //    public Account updateAccount(Long id, Account account) {
    //        Account existingAccount = getAccountById(id);
    //        existingAccount.setName(account.getName());
    //        existingAccount.setEmail(account.getEmail());
    //        existingAccount.setPhoneNumber(account.getPhoneNumber());
    //        existingAccount.setBalance(account.getBalance());
    //        return accountRepository.save(existingAccount);
    //    }

     public Account updateBalance(Long id, BigDecimal newBalance) {
        // Find the account by ID
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + id));

        // Update the account's balance
        account.setBalance(newBalance);

        // Save the updated account back to the database
        return accountRepository.save(account);
    }
    
        public void deleteAccount(Long id) {
            accountRepository.deleteById(id);
        }
    }




