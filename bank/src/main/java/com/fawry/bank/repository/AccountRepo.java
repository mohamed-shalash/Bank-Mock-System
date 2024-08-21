package com.fawry.bank.repository;

import com.fawry.bank.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {

    @Query(value = "SELECT * FROM account WHERE user_id = :id",nativeQuery = true)
    Account findByUserId(@Param("id") int id);

    @Query(value = "SELECT * FROM account WHERE card_number = :card",nativeQuery = true)
    Account findByCardNumber(@Param("card") String card);
    @Query(value = "SELECT * FROM account WHERE card_number = :card and password = :password",nativeQuery = true)
    Account findByCardNumberAndPassword(@Param("card") String card,@Param("password") String password);

    @Query(value = "SELECT * FROM account WHERE deposit  between :From and :To",nativeQuery = true)
    Page<Account> findByDepositBetween(@Param("From") double From, @Param("To") double To, Pageable pageable);

    @Query(value = "SELECT * FROM account where user_id = (Select id from user where user.email like :email)  ;",nativeQuery = true)
    List<Account> findByUserEmail(@Param("email") String email);

    @Query(value = "delete FROM account WHERE card_number = :card",nativeQuery = true)
    void deleteByCardNumber(@Param("card") String card);
}
