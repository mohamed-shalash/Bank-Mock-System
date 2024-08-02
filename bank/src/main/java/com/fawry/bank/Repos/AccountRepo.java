package com.fawry.bank.Repos;

import com.fawry.bank.Repos.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {

    @Query(value = "SELECT * FROM account WHERE user_id = :id",nativeQuery = true)
    Account findByUserId(@Param("id") int id);

    @Query(value = "SELECT * FROM account WHERE card_number = :card",nativeQuery = true)
    Account findByCardNumber(@Param("card") String card);

    @Query(value = "delete FROM account WHERE card_number = :card",nativeQuery = true)
    void deleteByCardNumber(@Param("card") String card);
}
