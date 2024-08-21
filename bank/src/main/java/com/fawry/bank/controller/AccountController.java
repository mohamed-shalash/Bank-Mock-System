package com.fawry.bank.controller;

import com.fawry.bank.dto.AmountDepositSearch;
import com.fawry.bank.dto.TransferDto;
import com.fawry.bank.models.AccountModule;
import com.fawry.bank.models.CardLogin;
import com.fawry.bank.service.AccountService;
import com.fawry.bank.dto.TransactionDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("accounts")
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

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountModule> getAccountByDeposit(@Valid @RequestBody AmountDepositSearch amountDepositSearch,
                                                   @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "3") int size){
        Pageable pageable = PageRequest.of(page, size);
        return accountService.getAccountByDeposit(amountDepositSearch.getFrom(),amountDepositSearch.getTo(),pageable);
    }

    @GetMapping(path = "/",params = {"email"})
    @ResponseStatus(HttpStatus.OK)
    public List<AccountModule> getAccountByUserEmail(@RequestParam("email") String email){
       // System.out.println(email);
        return accountService.getAccountByEmail(email);
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

    @PutMapping("/transactions")
    @ResponseStatus(HttpStatus.OK)
    public void drawFromAccount(@RequestBody TransactionDto transactional){
        if (transactional.getMethod().equals("Draw")) {
            accountService.drawFromAccount(transactional.getCardNumber(), transactional.getAmount());
        }
        else if (transactional.getMethod().equals("Add")) {
            accountService.addToAccount(transactional.getCardNumber(),transactional.getAmount());
        }
    }

    @PutMapping("/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    public void drawFromAccountToAnother(@Valid @RequestBody TransferDto transferDto) throws Exception {
        accountService.drawFromAccountToAnother(transferDto.getFrom(),transferDto.getTo(),transferDto.getAmount());
    }


    @DeleteMapping("/{card}")
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteAccount( @PathVariable String card){
        accountService.deleteAccount(card);
    }
}