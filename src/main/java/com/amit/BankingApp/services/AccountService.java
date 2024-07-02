package com.amit.BankingApp.services;

import com.amit.BankingApp.models.Account;
import com.amit.BankingApp.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public void add_new_account(String name, BigDecimal balance){
        Account acc = new Account();
        acc.setBalance(balance);
        acc.setName(name);
        accountRepository.save(acc);
    }
    public List<Account> get_all(){
        return accountRepository.findAll();
    }
    public Optional<Account> get_by_id(int id){
        return accountRepository.findById(id);
    }
    public void update_Balance(int id,BigDecimal new_balance){
        Optional<Account> optional = get_by_id(id);
        if(optional.isEmpty()){
            throw new RuntimeException();
        }
        Account account = optional.get();
        account.setBalance(new_balance);
        accountRepository.save(account);
    }
}
