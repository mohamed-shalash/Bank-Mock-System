package com.fawry.bank.service;

import com.fawry.bank.models.UserModule;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService  {
    public List<UserModule> getUsers();
    public UserModule getUser(String user_name, String password);

    public List<UserModule> getUser(String role, Pageable pageable);

    public void addUser(UserModule module);

    public void updateUser(UserModule module);

    public void delete(String email);

    UserModule getUserByEmail(String email);
}
