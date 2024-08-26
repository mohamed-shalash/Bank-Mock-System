package com.fawry.bank.aop;

import com.fawry.bank.entity.Logs;
import com.fawry.bank.entity.User;
import com.fawry.bank.models.UserModule;
import com.fawry.bank.repository.LogRepo;
import com.fawry.bank.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Aspect
@Component
@RequiredArgsConstructor
public class UserLogAspect {
    private final LogRepo logRepo;
    private final UserRepo userRepository;

    @After("execution(* com.fawry.bank.service.UserService.addUser(..)) && args(module)")
    public void logAfterAddUser(JoinPoint joinPoint, UserModule module) {
        logRepo.save(
                setLog("User_Add","Adding user " + module
                        , module.getEmail())
        );
    }

    @Before("execution(* com.fawry.bank.service.UserService.updateUser(..)) && args(module)")
    public void logBeforeUpdateUser(JoinPoint joinPoint, UserModule module) {
        User user = userRepository.findByEmail(module.getEmail()).orElseThrow();
        logRepo.save(
                setLog("User_Update",getLog(user,module)
                        ,user.getEmail())
        );
    }

    @After("execution(* com.fawry.bank.service.UserService.delete(..)) && args(email)")
    public void logAfterDeleteUser(JoinPoint joinPoint, String email) {
        logRepo.save(
                setLog("User_Delete","delete user with email "+ email,email)
        );
    }

    private Logs setLog(String kind, String logs, String email){
        return Logs.builder()
                .date(LocalDate.now())
                .time(LocalTime.now())
                .kind(kind)
                .log(logs)
                .email(email)
                .build();
    }

    private String getLog(User user, UserModule module){
        String logevent="";
        logevent +=(!user.getUserName().equals(module.getUserName()))?
                " User name From "+user.getUserName()+" To "+ module.getUserName()+" ":"";
        logevent +=(!user.getRole().equals(module.getRole()))?
                "Address city From "+user.getEmail()+" To "+module.getEmail() +" ":"";
        logevent +=(!user.getRole().equals(module.getRole()))?
                "Address Country From "+user.getRole()+" To "+module.getRole() +" ":"";
        return logevent;
    }
}
