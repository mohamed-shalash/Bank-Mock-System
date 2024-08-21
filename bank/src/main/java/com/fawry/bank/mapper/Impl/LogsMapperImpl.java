package com.fawry.bank.mapper.Impl;

import com.fawry.bank.entity.Logs;
import com.fawry.bank.mapper.LogsMapper;
import com.fawry.bank.models.LogModule;
import org.springframework.stereotype.Component;

@Component
public class LogsMapperImpl implements LogsMapper {
    @Override
    public Logs moduleToLogMapper(LogModule logModule) {
        return Logs.builder()
                .date(logModule.getDate())
                .time(logModule.getTime())
                .kind(logModule.getKind())
                .log(logModule.getLog())
                .email(logModule.getEmail())
                .build();
    }

    @Override
    public LogModule logToModuleMapper(Logs logs) {
        return LogModule.builder()
                .Date(logs.getDate())
                .Time(logs.getTime())
                .Kind(logs.getKind())
                .Log(logs.getLog())
                .Email(logs.getEmail())
                .build();
    }
}
