package com.amit.BankingApp.repositories;

import com.amit.BankingApp.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
