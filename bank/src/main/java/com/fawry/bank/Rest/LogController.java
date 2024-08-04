package com.fawry.bank.Rest;

import com.fawry.bank.Models.LogModule;
import com.fawry.bank.Service.LogService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("log")
public class LogController {

    private final LogService logService;
   @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LogModule> getLogs(){
        return logService.getLogs();
    }

    @GetMapping(params = {"date"})
    @ResponseStatus(HttpStatus.OK)
    public  List<LogModule> getLog( @RequestParam  LocalDate date){
        return logService.getLog(date);
    }

    @GetMapping(params = {"date","time"})
    @ResponseStatus(HttpStatus.OK)
    public  List<LogModule> getLog(@RequestParam LocalDate date, @RequestParam LocalTime time){
        return logService.getLog(date,time);
    }//?date=2024-03-15&time=20:55:18

    @GetMapping
    @RequestMapping("/{kind}")
    @ResponseStatus(HttpStatus.OK)
    public  List<LogModule> getLog(@PathVariable String kind){
        return logService.getLog(kind);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addLog( @RequestBody LogModule logModule){
        logService.addLog(logModule);
    }

}
