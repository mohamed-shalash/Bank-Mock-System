package com.fawry.bank.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    @NotNull(message = "Card Number can not be null")
    String CardNumber;
    @NotNull(message = "amount can not be null")
    @Positive(message = "amount cant be zero or negative")
    double amount;
    @NotNull(message = "method can not be null")
    String method;

}
