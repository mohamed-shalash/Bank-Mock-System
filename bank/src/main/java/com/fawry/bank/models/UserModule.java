package com.fawry.bank.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModule {
    String userName;
    String password;
    String role;
    String email;
}
