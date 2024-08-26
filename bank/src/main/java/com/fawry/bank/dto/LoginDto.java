package com.fawry.bank.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDto {
    @NotNull(message = "email can not be null")
    private String email;
    @NotNull(message = "password can not be null")
    private String password;
}