package com.fawry.bank.Models;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountModule {
    String CardNumber  ;
    double Deposit  ;
    String PhoneNumber ;
    UserModule user;
    AddressModule address;
}
