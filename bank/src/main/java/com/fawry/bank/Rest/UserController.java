package com.fawry.bank.Rest;

import com.fawry.bank.Models.UserModule;
import com.fawry.bank.Service.UserService;
import com.fawry.bank.dto.SignUpDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    List<UserModule> Users(){
        return userService.getUsers();
    }
    @GetMapping(params = {"email","pass"})
    UserModule User(@RequestParam String email,@RequestParam String pass){
        System.out.println(email+"   "+pass);
        return userService.getUser(email,pass);
    }

    @PostMapping
    void Add(@Valid @RequestBody UserModule userModule){
        System.out.println(userModule);
        userService.addUser(userModule);
    }

    @PutMapping
    void Update(@Valid @RequestBody UserModule userModule){
        userService.updateUser(userModule);
    }

    @DeleteMapping(params = {"email"})
    void Delete(@RequestParam String email){
        userService.delete(email);
    }
}
