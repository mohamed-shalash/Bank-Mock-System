package com.fawry.bank.Repos.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    long id;


    @Column(name="UserName")
    String user_name;


    @Column(name="Password")
    String password;

    @Column(name="Role")
    String role;

    @OneToMany(mappedBy = "user")
    List<Account> accounts;


}
