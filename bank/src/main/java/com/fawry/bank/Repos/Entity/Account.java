package com.fawry.bank.Repos.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    long id;


    @NotNull(message = "CardNumber cannot be null")
    @Column(name="card_number")
    String CardNumber  ;

    @NotNull(message = "Deposit cannot be null")
    @PositiveOrZero
    @Column(name="deposit")
    double Deposit  ;//balance

    @NotNull(message = "Phone number cannot be null")
    @Column(name="phonenumper")
    String PhoneNumber ;

    @ManyToOne
    @JoinColumn(name = "User_id",referencedColumnName = "id")
    User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    Address address;

}
