package com.fawry.bank.models;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AccountModule {
    @NotNull(message = "cardNumber cannot be null")
    String cardNumber  ;
    @NotNull(message = "deposit cannot be null")
    double deposit  ;
    @NotNull(message = "phoneNumber cannot be null")
    String phoneNumber ;
    @NotNull(message = "user cannot be null")
    UserModule user;
    @NotNull(message = "address cannot be null")
    AddressModule address;
    @NotNull(message = "password cannot be null")
    String password;
}
