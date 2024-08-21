package com.fawry.bank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@Entity
@Table(name = "logs")
@AllArgsConstructor
@NoArgsConstructor
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    long id;

    @NotNull(message = "Date cannot be null")
    @Column(name="log_date")
    LocalDate date ;

    @NotNull(message = "Time cannot be null")
    @Column(name="log_time")
    LocalTime time;

    @NotNull(message = "kind of log cannot be null")
    String kind;

    @NotNull(message = "Log cannot be null")
    String log;

    @NotNull(message = "email cannot be null")

    String email;
}
