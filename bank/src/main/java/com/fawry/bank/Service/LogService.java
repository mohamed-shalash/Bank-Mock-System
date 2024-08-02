package com.fawry.bank.Service;

import com.fawry.bank.Models.LogModule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface LogService {
    public List<LogModule> getLogs();
    public List<LogModule> getLog(LocalDate date);
    public List<LogModule> getLog(LocalDate date, LocalTime time);

    public List<LogModule> getLog(String kind);

    public void addLog(LogModule logModule);
}