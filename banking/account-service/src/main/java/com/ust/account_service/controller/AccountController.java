package com.ust.account_service.controller;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.account_service.model.Account;
import com.ust.account_service.service.AccountService;

@RestController
@RequestMapping("/api/accounts")

public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> getAllAccounts() {
     return accountService.getAllAccounts();
    }

   @GetMapping("/{id}")
   public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
       return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PostMapping 
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.createAccount(account));
    }

//    @PutMapping("/{id}/balance")
//    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
//        return ResponseEntity.ok(accountService.updateAccount(id, account));
//    }
@PutMapping("/{id}/balance")
public ResponseEntity<Account> updateBalance(@PathVariable Long id, @RequestBody BigDecimal newBalance) {
    Account updatedAccount = accountService.updateBalance(id, newBalance);
    return ResponseEntity.ok(updatedAccount);
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}



