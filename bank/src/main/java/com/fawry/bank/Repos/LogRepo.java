package com.fawry.bank.Repos;

import com.fawry.bank.Repos.Entity.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Repository
public interface LogRepo extends JpaRepository<Logs,Long> {

    @Query(value = "SELECT * FROM Logs WHERE log_date = :date", nativeQuery = true)
    List<Logs> findByDate(@Param("date") LocalDate date);

    @Query(value = "SELECT * FROM Logs WHERE log_date = :date and log_time =:time", nativeQuery = true)
    List<Logs> findByDateAndTime(@Param("date") LocalDate date, @Param("time") LocalTime time);

    @Query(value = "SELECT * FROM Logs WHERE kind = :kind", nativeQuery = true)
    List<Logs> findByKind(@Param("kind") String kind);
}
