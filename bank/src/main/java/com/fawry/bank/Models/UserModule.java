package com.fawry.bank.Models;

import com.fawry.bank.Repos.Entity.Account;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserModule {
    String user_name;
    String password;
    String role;
    List<Account> accounts;
}
