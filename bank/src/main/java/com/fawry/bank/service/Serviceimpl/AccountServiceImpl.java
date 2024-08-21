package com.fawry.bank.service.Serviceimpl;

import com.fawry.bank.mapper.AccountMapper;
import com.fawry.bank.mapper.AddressMapper;
import com.fawry.bank.mapper.UserMapper;
import com.fawry.bank.models.AccountModule;
import com.fawry.bank.models.AddressModule;
import com.fawry.bank.models.CardLogin;
import com.fawry.bank.models.UserModule;
import com.fawry.bank.models.UserModule;
import com.fawry.bank.repository.AccountRepo;
import com.fawry.bank.entity.Account;
import com.fawry.bank.entity.Address;
import com.fawry.bank.entity.Logs;
import com.fawry.bank.entity.User;
import com.fawry.bank.repository.LogRepo;
import com.fawry.bank.repository.UserRepo;
import com.fawry.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;
    private final UserRepo userRepo;
    private final LogRepo logRepo;

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
        Account account=accountRepo.findByCardNumber(CardNumber);
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
    public void addAccount(AccountModule accountModule) {
        Account account =accountMapper.moduleToAccountMapper(accountModule);
        accountRepo.save(account);
        logRepo.save(
                setLog("Add","Add new Card with number "+accountModule.getCardNumber()+" and deposit "+accountModule.getDeposit(),account.getUser().getEmail())
        );
    }


    @Transactional
    @Override
    public void updateAccount(AccountModule accountModule) {
       Account account =accountRepo.findByCardNumber(accountModule.getCardNumber());

        String logevent=getLog(accountModule,account);
        long id= account.getId();
        account =accountMapper.moduleToAccountMapper(accountModule);
        account.setId(id);

        accountRepo.save(account);

        logRepo.save(
                setLog("Update",logevent,account.getUser().getEmail())
        );

    }

    private String getLog(AccountModule accountModule,Account account){
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

    private Logs setLog(String kind,String logs,String email){
        return Logs.builder()
                .date(LocalDate.now())
                .time(LocalTime.now())
                .kind(kind)
                .log(logs)
                .email(email)
                .build();
    }

    @Transactional
    @Override
    public void drawFromAccount(String CardNumber,double amount) {//deposit with draw
        Account  account= accountRepo.findByCardNumber(CardNumber);
        if(amount >account.getDeposit()) throw new RuntimeException("Cant Draw amount is larger than your balance");

        account.setDeposit(account.getDeposit()-amount);

        accountRepo.save(account);

        logRepo.save(
                setLog(  "Draw","Draw From "+CardNumber+" Amount "+amount
                        ,account.getUser().getEmail())
        );
    }

    @Transactional
    @Override
    public void drawFromAccountToAnother(String FromCardNumber,String ToCardNumber,double amount) {
        Account from =accountRepo.findByCardNumber(FromCardNumber);
        Account to =accountRepo.findByCardNumber(ToCardNumber);

        if(amount >from.getDeposit()) throw new RuntimeException("Cant Draw amount is larger than your balance");
        from.setDeposit(from.getDeposit()-amount);
        to.setDeposit(to.getDeposit()+amount);

        accountRepo.save(from);
        accountRepo.save(to);

        logRepo.save(
                setLog("Draw", "Draw From "+FromCardNumber+" To "+ToCardNumber+" Amount "+amount
                        ,from.getUser().getEmail())
        );

        logRepo.save(
                setLog("Feed","Draw From "+FromCardNumber+" To "+ToCardNumber+" Amount "+amount
                        ,to.getUser().getEmail())
        );
    }

    @Override
    public void addToAccount(String cardNumber, double amount) {
        Account account = accountRepo.findByCardNumber(cardNumber);


        account.setDeposit(account.getDeposit()+amount);


        logRepo.save(
                setLog("Feed","Feed "+cardNumber+" With amount "+amount
                        ,account.getUser().getEmail())
        );

    }

    @Transactional
    @Override
    public void deleteAccount(String card) {
        Account account =accountRepo.findByCardNumber(card);

        accountRepo.deleteById(account.getId());
        logRepo.save(
                setLog("Delete","delete card "+card
                        ,account.getUser().getEmail())
        );
    }



}
