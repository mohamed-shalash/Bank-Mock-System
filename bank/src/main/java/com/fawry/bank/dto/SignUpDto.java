package com.fawry.bank.dto;

import lombok.Data;

@Data
public class SignUpDto {
    private String email;
    private String username;
    private String auth;
    private String password;

}
