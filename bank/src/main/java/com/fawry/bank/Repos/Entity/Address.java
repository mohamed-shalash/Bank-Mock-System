package com.fawry.bank.Repos.Entity;

import jakarta.persistence.*;
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

    @Column(name="country")
    String Country;

    @Column(name="state")
    String State;

    @Column(name="city")
    String City;

    @Column(name="streate")
    String Streate;

    @Column(name="houseID")
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
