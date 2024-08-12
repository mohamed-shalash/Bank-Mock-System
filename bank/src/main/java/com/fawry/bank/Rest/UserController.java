package com.fawry.bank.Rest;

import com.fawry.bank.Models.UserModule;
import com.fawry.bank.Service.UserService;
import com.fawry.bank.dto.SignUpDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
        //System.out.println(email+"   "+pass);
        return userService.getUser(email,pass);
    }

    @GetMapping(params = {"email"})
    @ResponseStatus(HttpStatus.OK)
    UserModule GetUser(@RequestParam String email){
        //System.out.println(email);
        return userService.getUserByEmail(email);
    }

    @GetMapping(params = {"role"})
    @ResponseStatus(HttpStatus.OK)
    List<UserModule> User(@RequestParam String role){
        return userService.getUser(role);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    String Add(@Valid @RequestBody UserModule userModule){
        //System.out.println(userModule);
        userService.addUser(userModule);
        return "Added";
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    void Update(@Valid @RequestBody UserModule userModule){
        //System.out.println(userModule);
        userService.updateUser(userModule);
        //return "Updated";
    }

    @DeleteMapping(params = {"email"})
    @ResponseStatus(HttpStatus.CREATED)
    void Delete(@RequestParam String email){
        //System.out.println(email);
        userService.delete(email);
    }
}
