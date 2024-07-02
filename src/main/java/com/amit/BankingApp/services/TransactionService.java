package com.amit.BankingApp.services;

import com.amit.BankingApp.models.Account;
import com.amit.BankingApp.models.Transaction;
import com.amit.BankingApp.models.TransactionType;
import com.amit.BankingApp.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountService accountService;

    public void createTransaction(int id, BigDecimal amount){
        Account account = accountService.get_by_id(id).orElseThrow(()-> new RuntimeException("no valid account"));
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        account.setBalance(account.getBalance().add(amount));
        transaction.setDate( LocalDateTime.now());
        accountService.update_Balance(account.getId(),account.getBalance());
        transactionRepository.save(transaction);
    }
    public void createTransaction(int id, BigDecimal amount,TransactionType transactionType){
        Account account = accountService.get_by_id(id).orElseThrow(()-> new RuntimeException("no valid account"));
        Transaction transaction = new Transaction();
        transaction.setAccount(new Account(account));
        transaction.setAmount(amount);
        transaction.setTransactionType(transactionType);
        account.setBalance(account.getBalance().add(amount));
        transaction.setDate( LocalDateTime.now());
        accountService.update_Balance(account.getId(),account.getBalance());
        transactionRepository.save(transaction);
    }
    public List<Transaction> allTransaction(){
        return transactionRepository.findAll();
    }
    @Transactional
    public void transfer(int id, int to_id, BigDecimal amount){
        createTransaction(id,amount.negate(),TransactionType.TRANSFER);
        createTransaction(to_id,amount,TransactionType.TRANSFER);
    }
}
