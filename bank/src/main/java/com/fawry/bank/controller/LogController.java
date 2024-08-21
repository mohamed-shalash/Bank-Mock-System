package com.fawry.bank.controller;

import com.fawry.bank.models.LogModule;
import com.fawry.bank.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


//todo use pagination  if you have 1000 record it will take time to return it
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("logs")
public class LogController {

    private final LogService logService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LogModule> getLogs(){
        return logService.getLogs();
    }

    @GetMapping(params = {"date"})
    @ResponseStatus(HttpStatus.OK)
    public  List<LogModule> getLog( @RequestParam  LocalDate date,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "3") int size){
        Pageable pageable = PageRequest.of(page, size);
        return logService.getLog(date,pageable);
    }

    @GetMapping(params = {"email"})
    @ResponseStatus(HttpStatus.OK)
    public  List<LogModule> getLogByEmail( @RequestParam  String email){
        return logService.getLogByEmail(email);
    }

    @GetMapping(params = {"date","timeFrom","timeTo"})
    @ResponseStatus(HttpStatus.OK)
    public  List<LogModule> getLog(@RequestParam LocalDate date, @RequestParam LocalTime timeFrom,@RequestParam LocalTime timeTo,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "3") int size){
        Pageable pageable = PageRequest.of(page, size);
        return logService.getLog(date,timeFrom,timeTo,pageable);
    }//?date=2024-03-15&time=20:55:18

    @GetMapping
    @RequestMapping("/{kind}")
    @ResponseStatus(HttpStatus.OK)
    public  List<LogModule> getLog(@PathVariable String kind,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "3") int size){
        Pageable pageable = PageRequest.of(page, size);
        return logService.getLog(kind,pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addLog( @RequestBody LogModule logModule){
        logService.addLog(logModule);
    }

}
