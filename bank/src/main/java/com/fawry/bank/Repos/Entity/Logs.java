package com.fawry.bank.Repos.Entity;

import jakarta.persistence.*;
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

    @Column(name="log_date")
    LocalDate Date ;

    @Column(name="log_time")
    LocalTime Time;

    @Column(name="kind")
    String Kind;

    @Column(name="log")
    String Log;


}
