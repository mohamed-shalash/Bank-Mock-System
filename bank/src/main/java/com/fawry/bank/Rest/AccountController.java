package com.fawry.bank.Rest;

import com.fawry.bank.Models.AccountModule;
import com.fawry.bank.Service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AccountModule> getAccounts(){
        return accountService.getAccounts();
    }

    @GetMapping("/{card}")
    @ResponseStatus(HttpStatus.OK)
    public AccountModule getAccountBycard(@PathVariable String card){
        return accountService.getAccountBycard(card);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addAccount(@RequestBody AccountModule account){
        accountService.addAccount(account);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void updateAccount(@RequestBody  AccountModule account){
        accountService.updateAccount(account);
    }

    @PutMapping("/{CardNumber}/{amount}")
    @ResponseStatus(HttpStatus.CREATED)
    public String DrawFromAccount(@PathVariable String CardNumber,@PathVariable double amount){
        return accountService.DrawFromAccount(CardNumber,amount);
    }

    @PutMapping(value = "/{CardNumber}",params = {"amount"})
    @ResponseStatus(HttpStatus.CREATED)
    public String AddToAccount(@PathVariable String CardNumber,@RequestParam double amount){
        return accountService.AddToAccount(CardNumber,amount);
    }

    @PutMapping("/{FromCardNumber}/{ToCardNumber}/{amount}")
    @ResponseStatus(HttpStatus.CREATED)
    public String DrawFromAccountToAnother(@PathVariable String FromCardNumber,@PathVariable  String ToCardNumber,@PathVariable  double amount){
        return accountService.DrawFromAccountToAnother(FromCardNumber,ToCardNumber,amount);
    }

    @DeleteMapping("/{card}")
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteAccount(@PathVariable String card){
        accountService.deleteAccount(card);
    }
}
