package com.fawry.bank.Service;

import com.fawry.bank.Models.UserModule;

import java.util.List;

public interface UserService  {
    public List<UserModule> getUsers();
    public UserModule getUser(String user_name,String password);

    public List<UserModule> getUser(String role);

    public void addUser(UserModule module);

    public void updateUser(UserModule module);

    public void delete(String email);

    UserModule getUserByEmail(String email);
}
