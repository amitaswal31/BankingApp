package com.amit.BankingApp.controllers;

import com.amit.BankingApp.models.Account;
import com.amit.BankingApp.services.AccountService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping(path = "/add")
    public String add_account(@RequestParam String name,
                            @RequestParam BigDecimal balance){
        try{accountService.add_new_account(name,balance);}
        catch (Exception e){
            return "Failure";
        }
        return "Success";
    }
    @GetMapping(path = "/all")
    public List<Account> get_all(){
        return accountService.get_all();
    }


    @GetMapping(path = "/{id}")
    public Account getAccount(@PathVariable int id, HttpServletResponse response){
        Optional<Account> res= accountService.get_by_id(id);
        if(res.isEmpty()){
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return new Account();
        }
        return res.get();
    }
    @PutMapping(path = "/update")
    public String updateBalance(@RequestParam int id,
                              @RequestParam BigDecimal balance){
        try {
            accountService.update_Balance(id, balance);
        }
        catch (RuntimeException e){
            return "Failure";
            //e.printStackTrace();
        }
        return "Success";
    }
}
