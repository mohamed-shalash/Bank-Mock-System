package com.fawry.bank.Service.Serviceimpl;

import com.fawry.bank.Models.AccountModule;
import com.fawry.bank.Models.AddressModule;
import com.fawry.bank.Models.UserModule;
import com.fawry.bank.Repos.AccountRepo;
import com.fawry.bank.Repos.Entity.Account;
import com.fawry.bank.Repos.Entity.Address;
import com.fawry.bank.Repos.Entity.Logs;
import com.fawry.bank.Repos.Entity.User;
import com.fawry.bank.Repos.LogRepo;
import com.fawry.bank.Repos.UserRepo;
import com.fawry.bank.Service.AccountService;
import lombok.RequiredArgsConstructor;
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

    @Override
    public List<AccountModule> getAccounts() {
        List<AccountModule> accountModules =accountRepo.findAll()
                .stream()
                .map(e->AccountModule.builder()
                        .CardNumber(e.getCardNumber())
                        .address(AddressModule.builder()
                                .City(e.getAddress().getCity())
                                .Country(e.getAddress().getCountry())
                                .State(e.getAddress().getState())
                                .HouseID(e.getAddress().getHouseID())
                                .Streate(e.getAddress().getStreate())
                                .build()
                        )
                        .Deposit(e.getDeposit())
                        .PhoneNumber(e.getPhoneNumber())
                        .user(UserModule.builder()
                                .user_name(e.getUser().getUser_name())
                                .password(e.getUser().getPassword())
                                .role(e.getUser().getRole())
                                .build())
                        .build()
                )
                .toList();
        return accountModules;
    }

    @Override
    public AccountModule getAccount(int User_id) {
        Account account=accountRepo.findByUserId(User_id);
        return AccountModule.builder()
                .user(UserModule.builder()
                        .user_name(account.getUser().getUser_name())
                        .password(account.getUser().getPassword())
                        .role(account.getUser().getRole())
                        .build())
                .CardNumber(account.getCardNumber())
                .address(AddressModule.builder()
                        .City(account.getAddress().getCity())
                        .Country(account.getAddress().getCountry())
                        .State(account.getAddress().getState())
                        .HouseID(account.getAddress().getHouseID())
                        .Streate(account.getAddress().getStreate())
                        .build())
                .Deposit(account.getDeposit())
                .PhoneNumber(account.getPhoneNumber())
                .build();
    }

    @Override
    public AccountModule getAccountBycard(String CardNumber) {
        Account account=accountRepo.findByCardNumber(CardNumber);
        return AccountModule.builder()
                .user(UserModule.builder()
                        .user_name(account.getUser().getUser_name())
                        .password(account.getUser().getPassword())
                        .role(account.getUser().getRole())
                        .build())
                .CardNumber(account.getCardNumber())
                .address(AddressModule.builder()
                        .City(account.getAddress().getCity())
                        .Country(account.getAddress().getCountry())
                        .State(account.getAddress().getState())
                        .HouseID(account.getAddress().getHouseID())
                        .Streate(account.getAddress().getStreate())
                        .build())
                .Deposit(account.getDeposit())
                .PhoneNumber(account.getPhoneNumber())
                .build();
    }

    @Transactional
    @Override
    public void addAccount(AccountModule accountModule) {
        Account account =new Account();
        Address address =new Address();
        account.setDeposit(accountModule.getDeposit());
        account.setPhoneNumber(accountModule.getPhoneNumber());
        account.setCardNumber(accountModule.getCardNumber());

        address.setCity(accountModule.getAddress().getCity());
        address.setCountry(accountModule.getAddress().getCountry());
        address.setState(accountModule.getAddress().getState());
        address.setHouseID(accountModule.getAddress().getHouseID());
        address.setStreate(accountModule.getAddress().getStreate());
        account.setAddress(address);

        User user =userRepo.findByUsernameaAndPassword(accountModule.getUser().getUser_name(),accountModule.getUser().getPassword());
       /* if (user == null)
            return ;*/

        account.setUser(user);

        accountRepo.save(account);
        Logs log =new Logs();
        log.setDate(LocalDate.now() );
        log.setTime(LocalTime.now());
        log.setKind("Add");
        log.setLog("Add new Card with number "+accountModule.getCardNumber()+" and deposit "+accountModule.getDeposit());
        logRepo.save(log);
    }


    @Transactional
    @Override
    public void updateAccount(AccountModule accountModule) {
        Account account ;

       // try {
            account =accountRepo.findByCardNumber(accountModule.getCardNumber());
       /* } catch (Exception e) {
            return ;
        }*/

        Address address =new Address();

        User user =userRepo.findByUsernameaAndPassword(accountModule.getUser().getUser_name(),accountModule.getUser().getPassword());
        if (user == null)
            return ;

        String logevent="";
        if(accountModule.getDeposit() != account.getDeposit()) {
            logevent +="Deposit From "+accountModule.getDeposit()+" To "+ account.getDeposit();
            account.setDeposit(accountModule.getDeposit());
        }

        if(!accountModule.getPhoneNumber().equals(account.getPhoneNumber()) ) {
            logevent +=" PhoneNumber From "+accountModule.getPhoneNumber()+" To "+ account.getPhoneNumber();
            account.setPhoneNumber(accountModule.getPhoneNumber());
        }


        if(!accountModule.getAddress().getCity().equals(account.getAddress().getCity()) ) {
            logevent +="Address city From "+accountModule.getAddress().getCity()+" To "+ account.getAddress().getCity();
            address.setCity(accountModule.getAddress().getCity());
        }

        if(!accountModule.getAddress().getCountry().equals(account.getAddress().getCountry()) ) {
            logevent +="Address Country From "+accountModule.getAddress().getCountry()+" To "+ account.getAddress().getCountry();
            address.setCountry(accountModule.getAddress().getCountry());
        }

        if(!accountModule.getAddress().getState().equals(account.getAddress().getState()) ) {
            logevent +="Address State From "+accountModule.getAddress().getState()+" To "+ account.getAddress().getState();
            address.setState(accountModule.getAddress().getState());
        }

        if(!accountModule.getAddress().getHouseID().equals(account.getAddress().getHouseID()) ) {
            logevent +="Address city From "+accountModule.getAddress().getHouseID()+" To "+ account.getAddress().getHouseID();
            address.setHouseID(accountModule.getAddress().getHouseID());
        }

        if(!accountModule.getAddress().getStreate().equals(account.getAddress().getStreate()) ) {
            logevent +="Address city From "+accountModule.getAddress().getStreate()+" To "+ account.getAddress().getStreate();
            address.setStreate(accountModule.getAddress().getStreate());
        }

        account.setAddress(address);


        account.setUser(user);
        accountRepo.save(account);
        Logs log =new Logs();
        log.setDate(LocalDate.now() );
        log.setTime(LocalTime.now());
        log.setKind("Update");
        //log.setLog(logevent);
        log.setLog(logevent);
        logRepo.save(log);
    }

    @Transactional
    @Override
    public String DrawFromAccount(String CardNumber,double amount) {//deposit with draw
        Account account;
        try {
             account = accountRepo.findByCardNumber(CardNumber);
        }catch (Exception e){
            return "account card number may be wrong";
        }

        if(amount >account.getDeposit()) return "Cant Draw";

        account.setDeposit(account.getDeposit()-amount);

        accountRepo.save(account);
        Logs log =new Logs();
        log.setDate(LocalDate.now() );
        log.setTime(LocalTime.now());
        log.setKind("Draw");
        log.setLog("Draw From "+CardNumber+" Amount "+amount);
        logRepo.save(log);
        return "Done";
    }

    @Transactional
    @Override
    public String DrawFromAccountToAnother(String FromCardNumber,String ToCardNumber,double amount) {
        Account from,to ;

        try {
            from =accountRepo.findByCardNumber(FromCardNumber);
            to =accountRepo.findByCardNumber(ToCardNumber);
        }catch (Exception e){
            return "account card numbers may be wrong";
        }

        if(amount >from.getDeposit()) return "Cant Draw";
        from.setDeposit(from.getDeposit()-amount);
        to.setDeposit(to.getDeposit()+amount);

        accountRepo.save(from);
        accountRepo.save(to);
        Logs log =new Logs();
        log.setDate(LocalDate.now() );
        log.setTime(LocalTime.now());
        log.setKind("Draw");
        log.setLog("Draw From "+FromCardNumber+" To "+ToCardNumber+" Amount "+amount);
        logRepo.save(log);
        return "Done";
    }

    @Override
    public String AddToAccount(String cardNumber, double amount) {
        Account account;
        try {
            account = accountRepo.findByCardNumber(cardNumber);
        }catch (Exception e){
            return "account card number may be wrong";
        }

        account.setDeposit(account.getDeposit()+amount);

        accountRepo.save(account);
        Logs log =new Logs();
        log.setDate(LocalDate.now() );
        log.setTime(LocalTime.now());
        log.setKind("Feed");
        log.setLog("Feed "+cardNumber+" With amount "+amount);
        logRepo.save(log);
        return "Done";
    }

    @Transactional
    @Override
    public String deleteAccount(String card) {
        Account account ;
        try {
            account =accountRepo.findByCardNumber(card);
        }catch (Exception e){
            return "account card numbers may be wrong";
        }
        accountRepo.deleteById(account.getId());
        Logs log =new Logs();
        log.setDate(LocalDate.now() );
        log.setTime(LocalTime.now());
        log.setKind("Delete");
        log.setLog("delete card "+card);
        logRepo.save(log);
        return "Deleted";
    }



}
