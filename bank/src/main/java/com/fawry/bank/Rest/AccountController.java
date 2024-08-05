package com.fawry.bank.Rest;

import com.fawry.bank.Models.AccountModule;
import com.fawry.bank.Service.AccountService;
import com.fawry.bank.dto.TransactionDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
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
    public void addAccount(@Valid @RequestBody AccountModule account){
        accountService.addAccount(account);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void updateAccount(@Valid  @RequestBody  AccountModule account){
        accountService.updateAccount(account);
    }

    /*@PutMapping("/{CardNumber}/{amount}")
    @ResponseStatus(HttpStatus.CREATED)
    public void DrawFromAccount( @PathVariable String CardNumber, @PathVariable double amount){
         accountService.DrawFromAccount(CardNumber,amount);
    }

    @PutMapping(value = "/{CardNumber}",params = {"amount"})
    @ResponseStatus(HttpStatus.CREATED)
    public void AddToAccount(@PathVariable String CardNumber,@RequestParam double amount){
         accountService.AddToAccount(CardNumber,amount);
    }

    @PutMapping("/{FromCardNumber}/{ToCardNumber}/{amount}")
    @ResponseStatus(HttpStatus.CREATED)
    public void DrawFromAccountToAnother( @PathVariable String FromCardNumber, @PathVariable  String ToCardNumber, @PathVariable  double amount){
         accountService.DrawFromAccountToAnother(FromCardNumber,ToCardNumber,amount);
    }*/

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
