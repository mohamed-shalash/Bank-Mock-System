package com.fawry.bank.mapper.Impl;

import com.fawry.bank.entity.Account;
import com.fawry.bank.entity.Address;
import com.fawry.bank.entity.User;
import com.fawry.bank.mapper.AccountMapper;
import com.fawry.bank.mapper.AddressMapper;
import com.fawry.bank.mapper.UserMapper;
import com.fawry.bank.models.AccountModule;
import com.fawry.bank.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AccountMapperImpl implements AccountMapper {

    private final AddressMapper addressMapper;
    private final UserMapper userMapper;
    private final UserRepo userRepo;
    @Override
    public AccountModule accountToModuleMapper(Account account) {
        return AccountModule.builder()
                .cardNumber(account.getCardNumber())
                .address(addressMapper.moduleToAddressMapper(account.getAddress()) )
                .deposit(account.getDeposit())
                .phoneNumber(account.getPhoneNumber())
                .password(account.getPassword())
                .user(userMapper.userToModuleMapper(account.getUser()))
                .build();
    }

    @Override
    public Account moduleToAccountMapper(AccountModule module) {
        return Account.builder()
                .deposit(module.getDeposit())
                .phoneNumber(module.getPhoneNumber())
                .cardNumber(module.getCardNumber())
                .password(module.getPassword())
                .address(addressMapper.addressToModuleMapper(module.getAddress()))
                .user(userRepo.findByUsernameaAndPassword(module.getUser().getUserName(),module.getUser().getPassword()))
                .build();
    }
}
