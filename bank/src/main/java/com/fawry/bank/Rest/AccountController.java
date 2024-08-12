package com.fawry.bank.Rest;

import com.fawry.bank.Models.AccountModule;
import com.fawry.bank.Models.CardLogin;
import com.fawry.bank.Service.AccountService;
import com.fawry.bank.dto.TransactionDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
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
    @CrossOrigin
    @GetMapping("/{From}/{To}")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountModule> getAccountByDeposit(@PathVariable String From,@PathVariable String To){
        System.out.println(From+"   "+To);
        return accountService.getAccountByDeposit(From,To);
    }
    @PostMapping("/atm")
    @ResponseStatus(HttpStatus.OK)
    public AccountModule AtmLogin(@RequestBody CardLogin card){
        return accountService.getAccountByLogIn(card);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addAccount(@Valid @RequestBody AccountModule account){
        accountService.addAccount(account);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void updateAccount(@Valid  @RequestBody  AccountModule account){
        try {
            accountService.updateAccount(account);
        }catch (Exception e){
            System.out.println(e);
        }
    }


    @PutMapping("/transaction")
    @ResponseStatus(HttpStatus.OK)
    public void DrawFromAccount(@RequestBody TransactionDto transactional){
        if (transactional.getMethod().equals("Draw")) {
            accountService.DrawFromAccount(transactional.getCardNumber(), transactional.getAmount());
        }
        else if (transactional.getMethod().equals("Add")) {
            accountService.AddToAccount(transactional.getCardNumber(),transactional.getAmount());
        }
    }

    @PutMapping("/{FromCardNumber}/{ToCardNumber}/{amount}")
    @ResponseStatus(HttpStatus.CREATED)
    public void DrawFromAccountToAnother( @PathVariable String FromCardNumber, @PathVariable  String ToCardNumber, @PathVariable  double amount){
        accountService.DrawFromAccountToAnother(FromCardNumber,ToCardNumber,amount);
    }


    @DeleteMapping("/{card}")
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteAccount( @PathVariable String card){
        accountService.deleteAccount(card);
    }
}
