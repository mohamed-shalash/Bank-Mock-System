package com.fawry.bank.entity;

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

    @Column(name="card_number",nullable = false)
    String cardNumber  ;

    @PositiveOrZero
    @Column(name="deposit",nullable = false)
    double deposit  ;//balance

    @Column(name="phonenumper",nullable = false)
    String phoneNumber ;

    @Column(name="password",nullable = false)
    String password ;

    @ManyToOne
    @JoinColumn(name = "User_id",referencedColumnName = "id")
    User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    Address address;

    @Override
    public String toString() {
        String us="user: {"+
            "userName= "+user.getUserName()+","+
                    "password="+user.getPassword()+","+
                    "role="+user.getRole()+","+
                    "email="+user.getEmail()+","+
        "}";
        return "Account{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", deposit=" + deposit +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", user=" +  us +
                ", address=" + (address!= null ? address.getId(): "null") +
                '}';
    }
}
