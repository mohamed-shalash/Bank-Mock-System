package com.fawry.bank.service.Serviceimpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.fawry.bank.mapper.LogsMapper;
import com.fawry.bank.models.LogModule;
import com.fawry.bank.repository.LogRepo;
import com.fawry.bank.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {
    private final LogRepo logRepo;
    private final LogsMapper mapper;
    @Override
    public List<LogModule> getLogs() {
        List<LogModule> logs =logRepo.findAll().stream()
                .map(e-> mapper.logToModuleMapper(e))
                .toList();
        return logs;
    }

    @Override
    public List<LogModule> getLog(LocalDate date, Pageable pageable) {
        List<LogModule> logs = logRepo.findByDate(date,pageable).stream()
                .map(e->mapper.logToModuleMapper(e))
                .toList();
        return logs;
    }

    @Override
    public List<LogModule> getLog(LocalDate date, LocalTime timeFrom,LocalTime timeTo, Pageable pageable) {

        List<LogModule> logs = logRepo.findByDateAndTime(date,timeFrom,timeTo,pageable).stream()
                .map(e->mapper.logToModuleMapper(e))
                .toList();
        return logs;
    }

    @Override
    public List<LogModule> getLog(String kind, Pageable pageable) {
        List<LogModule> logs = logRepo.findByKind(kind,pageable).stream()
                .map(e->mapper.logToModuleMapper(e))
                .toList();
        return logs;
    }


    @Override
    public List<LogModule> getLogByEmail(String Email) {
        List<LogModule> logs = logRepo.findByEmail(Email).stream()
                .map(e->mapper.logToModuleMapper(e))
                .toList();
        return logs;
    }

    @Override
    public void addLog(LogModule logModule) {
        logRepo.save(mapper.moduleToLogMapper(logModule));
    }
}
