package com.fawry.bank.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AmountDepositSearch {
    @NotNull(message = "Give me from key word ")
    @Positive(message = "from must be double positive value")
    double from;
    @NotNull(message = "Give me to key word ")
    @Positive(message = "to must be double positive value")
    double to;
}
