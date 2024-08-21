package com.fawry.bank.service;

import com.fawry.bank.models.LogModule;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface LogService {
    public List<LogModule> getLogs();
    public List<LogModule> getLog(LocalDate date, Pageable pageable);
    public List<LogModule> getLog(LocalDate date, LocalTime timeFrom,LocalTime timeTo, Pageable pageable);

    public List<LogModule> getLog(String kind, Pageable pageable);

    public List<LogModule> getLogByEmail(String kind);

    public void addLog(LogModule logModule);
}
