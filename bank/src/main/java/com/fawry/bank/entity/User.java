package com.fawry.bank.entity;

import com.fawry.bank.entity.enumType.RoleType;
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

    @Column(name="userName",nullable = false)
    String userName;

    @Column(name="email",nullable = false)
    String email;

    @Column(name="Password",nullable = false)
    String password;

    /*@Column(name="Role",nullable = false)
    String role;*/
    @Column(name = "Role", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @OneToMany(mappedBy = "user")
    List<Account> accounts;



}
