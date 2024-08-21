package com.fawry.bank.mapper;

import com.fawry.bank.entity.User;
import com.fawry.bank.models.UserModule;

public interface UserMapper {
    UserModule userToModuleMapper(User user);
    User moduleToUserMapper(UserModule user);
}
