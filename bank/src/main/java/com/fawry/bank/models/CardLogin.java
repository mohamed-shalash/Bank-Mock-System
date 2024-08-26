package com.fawry.bank.models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardLogin {
    @NotNull(message = "Card Number cant be null")
    String cardNumber  ;
    @NotNull(message = "password cant be null")
    String password  ;
}
