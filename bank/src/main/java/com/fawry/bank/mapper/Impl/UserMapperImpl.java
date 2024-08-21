package com.fawry.bank.mapper.Impl;

import com.fawry.bank.entity.User;
import com.fawry.bank.mapper.UserMapper;
import com.fawry.bank.models.UserModule;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserModule userToModuleMapper(User user) {
        return UserModule.builder()
                .role(user.getRole())
                .email(user.getEmail())
                .userName(user.getUserName())
                .password(user.getPassword())
                .build();
    }

    @Override
    public User moduleToUserMapper(UserModule user) {
        return User.builder()
                .userName(user.getUserName())
                .role(user.getRole())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
