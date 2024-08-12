package com.fawry.bank.Models;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogModule {
    private LocalDate Date ;
    private LocalTime Time;
    private String Kind;
    private String Log;
}
