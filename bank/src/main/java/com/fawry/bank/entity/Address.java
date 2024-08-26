package com.fawry.bank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    long id;

    @Column(name="country",nullable = false)
    String Country;

    @Column(name="state",nullable = false)
    String State;

    @Column(name="city",nullable = false)
    String City;

    @Column(name="streate",nullable = false)
    String Streate;

    @Column(name="houseID",nullable = false)
    String HouseID;

    @OneToOne(mappedBy = "address")
    Account account;

    public Address(String country, String state, String city, String streate, String houseID) {
        Country = country;
        State = state;
        City = city;
        Streate = streate;
        HouseID = houseID;
    }
}
