package com.fawry.bank.mapper;

import com.fawry.bank.entity.Logs;
import com.fawry.bank.models.LogModule;

public interface LogsMapper {
    Logs moduleToLogMapper(LogModule logModule);
    LogModule logToModuleMapper(Logs logs);
}
