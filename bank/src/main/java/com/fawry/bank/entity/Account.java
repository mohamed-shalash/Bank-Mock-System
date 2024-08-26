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


    @NotNull(message = "CardNumber cannot be null")
    @Column(name="card_number")
    String cardNumber  ;

    @NotNull(message = "Deposit cannot be null")
    @PositiveOrZero
    @Column(name="deposit")
    double deposit  ;//balance

    @NotNull(message = "Phone number cannot be null")
    @Column(name="phonenumper")
    String phoneNumber ;

    @NotNull(message = "password cannot be null")
    @Column(name="password")
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
