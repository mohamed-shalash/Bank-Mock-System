package com.fawry.bank.controller;

import com.fawry.bank.models.UserModule;
import com.fawry.bank.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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
        return userService.getUser(email,pass);
    }

    @GetMapping(params = {"email"})
    @ResponseStatus(HttpStatus.OK)
    UserModule GetUser(@RequestParam String email){
        return userService.getUserByEmail(email);
    }

    @GetMapping(params = {"role"})
    @ResponseStatus(HttpStatus.OK)
    List<UserModule> User(@RequestParam String role,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "3") int size){
        Pageable pageable = PageRequest.of(page, size);
        return userService.getUser(role,pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void Add(@Valid @RequestBody UserModule userModule){
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
        //System.out.println(email);
        userService.delete(email);
    }
}
