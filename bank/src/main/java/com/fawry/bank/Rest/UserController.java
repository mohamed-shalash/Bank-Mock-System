package com.fawry.bank.Rest;

import com.fawry.bank.Models.UserModule;
import com.fawry.bank.Service.UserService;
import com.fawry.bank.dto.SignUpDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<UserModule> Users(){
        return userService.getUsers();
    }

    @GetMapping(params = {"email","pass"})
    @ResponseStatus(HttpStatus.OK)
    UserModule User(@RequestParam String email,@RequestParam String pass){
        //System.out.println(email+"   "+pass);
        return userService.getUser(email,pass);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void Add(@Valid @RequestBody UserModule userModule){
        //System.out.println(userModule);
        userService.addUser(userModule);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    void Update(@Valid @RequestBody UserModule userModule){
        userService.updateUser(userModule);
    }

    @DeleteMapping(params = {"email"})
    @ResponseStatus(HttpStatus.CREATED)
    void Delete(@RequestParam String email){
        userService.delete(email);
    }
}
