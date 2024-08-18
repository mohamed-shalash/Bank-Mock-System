package com.fawry.bank.Repos;

import com.fawry.bank.Repos.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    @Query(value = "SELECT * FROM user WHERE user_name = :user_name and password = :password",nativeQuery = true)
    User findByUsernameaAndPassword(@Param("user_name") String user_name,@Param("password") String password);

    @Query(value = "SELECT * FROM user WHERE email = :email and password = :password",nativeQuery = true)
    User findByEmailAndPassword(@Param("email") String email,@Param("password") String password);

    @Query(value = "SELECT * FROM user WHERE email = :email ",nativeQuery = true)
    Optional<User> findByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM user WHERE role = :role ",nativeQuery = true)
    List<User> findByRole(@Param("role") String role);
}
