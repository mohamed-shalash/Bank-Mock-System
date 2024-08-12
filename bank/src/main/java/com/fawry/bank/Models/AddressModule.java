package com.fawry.bank.Models;

import com.fawry.bank.Repos.Entity.Account;
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
