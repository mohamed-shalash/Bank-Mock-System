package com.fawry.bank.Repos;

import com.fawry.bank.Repos.Entity.Account;
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
//select * from account where card_number ="3683021" and password = "1234"
    @Query(value = "SELECT * FROM account WHERE card_number = :card and password = :password",nativeQuery = true)
    Account findByCardNumberAndPassword(@Param("card") String card,@Param("password") String password);

    @Query(value = "SELECT * FROM account WHERE deposit  between :From and :To",nativeQuery = true)
    List<Account> findByDepositBetween(@Param("From") String From, @Param("To") String To);

    @Query(value = "delete FROM account WHERE card_number = :card",nativeQuery = true)
    void deleteByCardNumber(@Param("card") String card);
}
