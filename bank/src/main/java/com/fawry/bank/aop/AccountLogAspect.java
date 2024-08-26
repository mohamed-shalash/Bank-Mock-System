package com.fawry.bank.aop;

import com.fawry.bank.entity.Account;
import com.fawry.bank.entity.Logs;
import com.fawry.bank.handler.RecordNotFoundException;
import com.fawry.bank.models.AccountModule;
import com.fawry.bank.repository.AccountRepo;
import com.fawry.bank.repository.LogRepo;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Aspect
@Component
@RequiredArgsConstructor
public class AccountLogAspect {

    private final LogRepo logRepo;
    private final AccountRepo accountRepo;

    @After("execution(* com.fawry.bank.service.AccountService.addAccount(..)) && args(accountModule)")
    public void logAfterAddAccount(JoinPoint joinPoint, AccountModule accountModule) {
        logRepo.save(
                setLog("Add", "Add new Card with number " + accountModule.getCardNumber() + " and deposit " + accountModule.getDeposit(), accountModule.getUser().getEmail())
        );
    }

    @Before("execution(* com.fawry.bank.service.AccountService.updateAccount(..)) && args(accountModule)")
    public void logAfterUpdateAccount(JoinPoint joinPoint, AccountModule accountModule) {
        Account account =accountRepo.findByCardNumber(accountModule.getCardNumber()).orElseThrow(()->new RecordNotFoundException("Card is not found"));
        logRepo.save(
                setLog("Update",getLog(accountModule,account),account.getUser().getEmail())
        );
    }

    @After("execution(* com.fawry.bank.service.AccountService.drawFromAccount(..)) && args(CardNumber,amount)")
    public void logAfterDrawFromAccount(JoinPoint joinPoint,String CardNumber,double amount) {
        Account  account= accountRepo.findByCardNumber(CardNumber).orElseThrow(()->new RecordNotFoundException("Card is not found"));
        logRepo.save(
               setLog(  "Draw","Draw From "+CardNumber+" Amount "+amount
                        ,account.getUser().getEmail())
        );
    }

    @After("execution(* com.fawry.bank.service.AccountService.drawFromAccountToAnother(..)) && args(FromCardNumber,ToCardNumber,amount)")
    public void logAfterDrawFromAccountToAnother(JoinPoint joinPoint, String FromCardNumber,String ToCardNumber,double amount) {
        Account from =accountRepo.findByCardNumber(FromCardNumber).orElseThrow(()->new RecordNotFoundException("Card From is not found"));
        Account to =accountRepo.findByCardNumber(ToCardNumber).orElseThrow(()->new RecordNotFoundException("Card To is not found"));
        logRepo.save(
                setLog("Draw", "Draw From "+FromCardNumber+" To "+ToCardNumber+" Amount "+amount
                        ,from.getUser().getEmail())
        );

        logRepo.save(
                setLog("Feed","Draw From "+FromCardNumber+" To "+ToCardNumber+" Amount "+amount
                        ,to.getUser().getEmail())
        );
    }

    @After("execution(* com.fawry.bank.service.AccountService.addToAccount(..)) && args(cardNumber,amount)")
    public void logAfterAddToAccount(JoinPoint joinPoint,String cardNumber, double amount) {
        Account account = accountRepo.findByCardNumber(cardNumber).orElseThrow(()->new RecordNotFoundException("Card is not found"));
        logRepo.save(
                setLog("Feed","Feed "+cardNumber+" With amount "+amount
                        ,account.getUser().getEmail())
        );
    }

    @Before("execution(* com.fawry.bank.service.AccountService.deleteAccount(..)) && args(card)")
    public void logAfterDeleteAccount(JoinPoint joinPoint, String card) {
        Account account =accountRepo.findByCardNumber(card).orElseThrow(()->new RecordNotFoundException("Card is not found "));
        logRepo.save(
                setLog("Delete","delete card "+card
                        ,account.getUser().getEmail())
        );
    }

    private Logs setLog(String kind, String logs, String email){
        return Logs.builder()
                .date(LocalDate.now())
                .time(LocalTime.now())
                .kind(kind)
                .log(logs)
                .email(email)
                .build();
    }
    private String getLog(AccountModule accountModule, Account account){
        String logevent="";
        logevent +=(accountModule.getDeposit() != account.getDeposit())?
                "Deposit From "+account.getDeposit() +" To "+ accountModule.getDeposit()+" ":"";
        logevent +=(!accountModule.getPhoneNumber().equals(account.getPhoneNumber()))?
                " PhoneNumber From "+account.getPhoneNumber()+" To "+ accountModule.getPhoneNumber()+" ":"";
        logevent +=(!accountModule.getPassword().equals(account.getPassword()))?
                " password From "+account.getPassword()+" To "+ accountModule.getPassword()+" ":"";
        logevent +=(!accountModule.getAddress().getCity().equals(account.getAddress().getCity()))?
                "Address city From "+account.getAddress().getCity()+" To "+accountModule.getAddress().getCity() +" ":"";
        logevent +=(!accountModule.getAddress().getCountry().equals(account.getAddress().getCountry()))?
                "Address Country From "+account.getAddress().getCountry()+" To "+accountModule.getAddress().getCountry() +" ":"";
        logevent +=(!accountModule.getAddress().getState().equals(account.getAddress().getState()))?
                logevent +="Address State From "+account.getAddress().getState()+" To "+accountModule.getAddress().getState() +"":"";
        logevent +=(!accountModule.getAddress().getHouseID().equals(account.getAddress().getHouseID()))?
                "Address city From "+account.getAddress().getHouseID()+" To "+ accountModule.getAddress().getHouseID()+" ":"";
        logevent +=(!accountModule.getAddress().getStreate().equals(account.getAddress().getStreate()) )?
                "Address city From "+ account.getAddress().getStreate()+" To "+accountModule.getAddress().getStreate()+" ":"";
        return logevent;
    }
}
