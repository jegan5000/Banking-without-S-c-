package com.ust.account_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.account_service.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}