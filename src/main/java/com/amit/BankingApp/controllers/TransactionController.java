package com.amit.BankingApp.controllers;

import com.amit.BankingApp.models.Transaction;
import com.amit.BankingApp.models.TransactionType;
import com.amit.BankingApp.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public String createTransDeposit(@RequestParam int id,
                            @RequestParam BigDecimal amount){
        try{transactionService.createTransaction(id,amount);}
        catch (RuntimeException e){
            return "No account Exists";
        }
        return "Success";
    }
    @PostMapping(path = "/withdraw")
    public String createTransWithdraw(@RequestParam int id,
                                     @RequestParam BigDecimal amount){
        try{transactionService.createTransaction(id,amount.negate(), TransactionType.WITHDRAW);}
        catch (RuntimeException e){
            return "No account Exists";
        }
        return "Success";
    }
    @GetMapping(path= "/all")
    public List<Transaction> getTransactions(){
        return transactionService.allTransaction();
    }

    @PostMapping(path = "/transfer")
    public String transfer(@RequestParam int id,
                          @RequestParam int to_id,
                          @RequestParam BigDecimal amount){
        try{
            transactionService.transfer(id,to_id,amount);
        }
        catch(RuntimeException e){
            return "transaction failed";
        }
        return "success";
    }
}
