package com.fawry.bank.Service;

import com.fawry.bank.Models.AccountModule;
import java.util.List;

public interface AccountService {
    public List<AccountModule> getAccounts();
    public AccountModule getAccount(int User_id);

    public AccountModule getAccountBycard(String CardNumber);

    public void addAccount(AccountModule account);


    public void updateAccount(AccountModule account);

    public String DrawFromAccount(String CardNumber,double amount);

    public String DrawFromAccountToAnother(String FromCardNumber,String ToCardNumber,double amount);

    public String deleteAccount(String card);

    String AddToAccount(String cardNumber, double amount);
}