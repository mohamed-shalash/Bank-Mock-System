package com.fawry.bank.models;

import com.fawry.bank.entity.enumType.RoleType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModule {
    @NotNull(message = "User Name Must be Not Null")
    String userName;
    @NotNull(message = "password Must be Not Null")
    String password;
    @NotNull(message = "role Must be Not Null")
    RoleType role;
    @NotNull(message = "email Must be Not Null")
    String email;
}
