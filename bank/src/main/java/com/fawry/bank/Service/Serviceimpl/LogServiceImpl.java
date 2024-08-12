package com.fawry.bank.Service.Serviceimpl;

import com.fawry.bank.Models.LogModule;
import com.fawry.bank.Repos.Entity.Logs;
import com.fawry.bank.Repos.LogRepo;
import com.fawry.bank.Service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {
    private final LogRepo logRepo;
    @Override
    public List<LogModule> getLogs() {
        List<LogModule> logs =logRepo.findAll().stream()
                .map(e->LogModule.builder()
                        .Date(e.getDate())
                        .Time(e.getTime())
                        .Kind(e.getKind())
                        .Log(e.getLog())
                        .build() ).toList();
        return logs;
    }

    @Override
    public List<LogModule> getLog(LocalDate date) {
        List<LogModule> logs = logRepo.findByDate(date).stream()
                .map(e->LogModule.builder()
                        .Date(e.getDate())
                        .Time(e.getTime())
                        .Kind(e.getKind())
                        .Log(e.getLog())
                        .build() ).toList();
        return logs;
    }

    @Override
    public List<LogModule> getLog(LocalDate date, LocalTime timeFrom,LocalTime timeTo) {

        List<LogModule> logs = logRepo.findByDateAndTime(date,timeFrom,timeTo).stream()
                .map(e->LogModule.builder()
                        .Date(e.getDate())
                        .Time(e.getTime())
                        .Kind(e.getKind())
                        .Log(e.getLog())
                        .build() ).toList();
        return logs;
    }

    @Override
    public List<LogModule> getLog(String kind) {
        List<LogModule> logs = logRepo.findByKind(kind).stream()
                .map(e->LogModule.builder()
                        .Date(e.getDate())
                        .Time(e.getTime())
                        .Kind(e.getKind())
                        .Log(e.getLog())
                        .build() ).toList();
        return logs;
    }

    @Override
    public void addLog(LogModule logModule) {
        //convert logmodule to log
        Logs log =new Logs();
        log.setDate(logModule.getDate());
        log.setTime(logModule.getTime());
        log.setKind(logModule.getKind());
        log.setLog(logModule.getLog());
        //add log
        logRepo.save(log);
    }
}
