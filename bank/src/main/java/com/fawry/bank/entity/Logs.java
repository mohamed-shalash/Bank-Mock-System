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
@Table(name = "bank_logs")
@AllArgsConstructor
@NoArgsConstructor
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    long id;

    @Column(name="log_date",nullable = false)
    LocalDate date ;

    @Column(name="log_time",nullable = false)
    LocalTime time;

    @Column(nullable = false)
    String kind;


    @Column(nullable = false)
    String log;

    @Column(nullable = false)
    String email;

}
