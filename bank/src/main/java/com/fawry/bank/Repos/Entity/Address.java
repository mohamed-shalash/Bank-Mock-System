package com.fawry.bank.Repos.Entity;

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

    @NotNull(message = "country cannot be null")
    @Column(name="country")
    String Country;

    @NotNull(message = "state cannot be null")
    @Column(name="state")
    String State;

    @NotNull(message = "city cannot be null")
    @Column(name="city")
    String City;

    @NotNull(message = "streate cannot be null")
    @Column(name="streate")
    String Streate;

    @NotNull(message = "houseID cannot be null")
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
