package com.fawry.bank.Repos.Entity;

import jakarta.persistence.*;
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

    @Column(name="card_number")
    String CardNumber  ;

    @Column(name="deposit")
    double Deposit  ;

    @Column(name="phonenumper")
    String PhoneNumber ;

    @ManyToOne
    @JoinColumn(name = "User_id",referencedColumnName = "id")
    User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    Address address;

}
