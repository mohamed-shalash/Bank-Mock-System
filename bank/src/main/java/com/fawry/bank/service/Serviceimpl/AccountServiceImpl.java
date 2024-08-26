package com.fawry.bank.service.Serviceimpl;

import com.fawry.bank.handler.ConflictException;
import com.fawry.bank.handler.RecordNotFoundException;
import com.fawry.bank.mapper.AccountMapper;
import com.fawry.bank.models.AccountModule;
import com.fawry.bank.models.CardLogin;
import com.fawry.bank.repository.AccountRepo;
import com.fawry.bank.entity.Account;
import com.fawry.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;
    private final AccountMapper accountMapper;
    @Override
    public List<AccountModule> getAccounts() {
        List<AccountModule> accountModules =accountRepo.findAll()
                .stream()
                .map(e->accountMapper.accountToModuleMapper(e) )
                .toList();
        return accountModules;
    }

    @Override
    public AccountModule getAccount(int User_id) {
        Account account=accountRepo.findByUserId(User_id);
        return accountMapper.accountToModuleMapper(account);
    }

    @Override
    public AccountModule getAccountBycard(String CardNumber) {
        Account account=accountRepo.findByCardNumber(CardNumber).orElseThrow(()->new RecordNotFoundException("Card is not found"));
        return accountMapper.accountToModuleMapper(account);
    }

    @Override
    public List<AccountModule> getAccountByDeposit(double From, double To, Pageable pageable) {
        return accountRepo.findByDepositBetween(From, To,pageable)
                .stream()
                .map(e->accountMapper.accountToModuleMapper(e))
                .toList();
    }

    @Override
    public List<AccountModule> getAccountByEmail(String Email) {
        List<AccountModule> accountModules =accountRepo.findByUserEmail(Email)
                .stream()
                .map(e->accountMapper.accountToModuleMapper(e))
                .toList();
        return accountModules;
    }

    @Override
    public AccountModule getAccountByLogIn(CardLogin card) {
        Account account=accountRepo.findByCardNumberAndPassword(card.getCardNumber(), card.getPassword());
        return accountMapper.accountToModuleMapper(account);
    }


    @Transactional
    @Override
    public String addAccount(AccountModule accountModule) {
        if(!accountRepo.findByCardNumber(accountModule.getCardNumber()).equals( Optional.empty())) {
            throw new ConflictException("Card number is already exists");
        }
        Account account =accountMapper.moduleToAccountMapper(accountModule);
        accountRepo.save(account);
        return "Account Added";
    }


    @Transactional
    @Override
    public void updateAccount(AccountModule accountModule) {
       Account account =accountRepo.findByCardNumber(accountModule.getCardNumber()).orElseThrow(()->new RecordNotFoundException("Card is not found"));;

        long id= account.getId();
        account =accountMapper.moduleToAccountMapper(accountModule);
        account.setId(id);
        accountRepo.save(account);
    }

    @Transactional
    @Override
    public void drawFromAccount(String CardNumber,double amount) {//deposit with draw
        Account  account= accountRepo.findByCardNumber(CardNumber).orElseThrow(()->new RecordNotFoundException("Card is not found"));
        if(amount >account.getDeposit()) throw new RuntimeException("Cant Draw amount is larger than your balance");

        account.setDeposit(account.getDeposit()-amount);

        accountRepo.save(account);
    }

    @Transactional
    @Override
    public void drawFromAccountToAnother(String FromCardNumber,String ToCardNumber,double amount) {
        Account from =accountRepo.findByCardNumber(FromCardNumber).orElseThrow(()->new RecordNotFoundException("Card From is not found"));;
        Account to =accountRepo.findByCardNumber(ToCardNumber).orElseThrow(()->new RecordNotFoundException("Card To is not found"));;
        if(amount >from.getDeposit()) throw new RuntimeException("Cant Draw amount is larger than your balance");
        from.setDeposit(from.getDeposit()-amount);
        to.setDeposit(to.getDeposit()+amount);
        accountRepo.save(from);
        accountRepo.save(to);
    }

    @Override
    public void addToAccount(String cardNumber, double amount) {
        Account account = accountRepo.findByCardNumber(cardNumber).orElseThrow(()->new RecordNotFoundException("Card is not found"));

        account.setDeposit(account.getDeposit()+amount);

    }

    @Transactional
    @Override
    public void deleteAccount(String card) {
        Account account = accountRepo.findByCardNumber(card).orElseThrow(()->new RecordNotFoundException("Card is not found"));
        accountRepo.deleteById(account.getId());
    }



}
