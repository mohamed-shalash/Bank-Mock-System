package com.fawry.bank.repository;

import com.fawry.bank.entity.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface LogRepo extends JpaRepository<Logs,Long> {

    @Query(value = "SELECT * FROM Logs WHERE log_date = :date", nativeQuery = true)
    Page<Logs> findByDate(@Param("date") LocalDate date, Pageable pageable);

    @Query(value = "SELECT * FROM Logs WHERE log_date = :date and log_time between :timeFrom and :timeTo", nativeQuery = true)
    Page<Logs> findByDateAndTime(@Param("date") LocalDate date, @Param("timeFrom") LocalTime timeFrom, @Param("timeTo") LocalTime timeTo, Pageable pageable);

    @Query(value = "SELECT * FROM Logs WHERE kind = :kind", nativeQuery = true)
    Page<Logs> findByKind(@Param("kind") String kind, Pageable pageable);

    @Query(value = "select * from logs where email = :email", nativeQuery = true)
    List<Logs> findByEmail(@Param("email") String email);
}
