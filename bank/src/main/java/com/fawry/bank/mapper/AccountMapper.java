package com.fawry.bank.mapper;

import com.fawry.bank.entity.Account;
import com.fawry.bank.models.AccountModule;
import org.springframework.context.annotation.Configuration;


public interface AccountMapper {
    AccountModule accountToModuleMapper(Account account);
    Account moduleToAccountMapper(AccountModule module);
}
