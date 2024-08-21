package com.fawry.bank.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransferDto {
    @NotNull(message = "Give me from key word with String Value")
    String from;
    @NotNull(message = "Give me to key word with String Value")
    String to;

    @NotNull(message = "Give me amount key word ")
    @Positive(message = "amount must be double positive value")
    double amount;

}
