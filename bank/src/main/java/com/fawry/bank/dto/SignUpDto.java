package com.fawry.bank.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpDto {
    @NotNull(message = "email can not be null")
    private String email;
    @NotNull(message = "user name can not be null")
    private String username;
    @NotNull(message = "authentication can not be null")
    private String auth;
    @NotNull(message = "password can not be null")
    private String password;

}
