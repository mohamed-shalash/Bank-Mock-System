package com.fawry.bank.service;

import com.fawry.bank.models.AccountModule;
import com.fawry.bank.models.CardLogin;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {
    public List<AccountModule> getAccounts();
    public AccountModule getAccount(int User_id);

    public AccountModule getAccountBycard(String CardNumber);

    public List<AccountModule> getAccountByDeposit(double From, double To, Pageable pageable);

    public List<AccountModule> getAccountByEmail(String Email);
    public String addAccount(AccountModule account);


    public void updateAccount(AccountModule account);

    public void drawFromAccount(String CardNumber,double amount);

    public void drawFromAccountToAnother(String FromCardNumber,String ToCardNumber,double amount) throws Exception;

    public void deleteAccount(String card);

    public void addToAccount(String cardNumber, double amount);

    public AccountModule getAccountByLogIn(CardLogin card);
}
