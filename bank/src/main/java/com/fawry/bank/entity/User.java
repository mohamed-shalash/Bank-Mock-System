package com.fawry.bank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "User Name cannot be null")
    @Column(name="userName")
    String userName;

    @NotNull(message = "email cannot be null")
    @Column(name="email")
    String email;


    @NotNull(message = "password cannot be null")
    @Size(min = 5, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(name="Password")
    String password;

    @NotNull(message = "role cannot be null")
    @Column(name="Role")
    String role;

    @OneToMany(mappedBy = "user")
    List<Account> accounts;



}
