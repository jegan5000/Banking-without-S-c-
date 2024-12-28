package com.ust.transaction_service.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccountDTO {
    private Long id;
    private String name;
    private BigDecimal balance;

     // Getter and Setter for id
     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for balance
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

