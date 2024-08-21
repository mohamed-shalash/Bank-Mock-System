package com.fawry.bank.models;

import com.fawry.bank.entity.Account;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressModule {
    String Country;
    String State;
    String City;
    String Streate;
    String HouseID;
    Account account;
}
