package com.fawry.bank.Rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
//@RequiredArgsConstructor
public class UserController {

    @GetMapping
    String home(){
        return "home";
    }

    @GetMapping
    @RequestMapping("/user")
    String User(){
        return "user";
    }

    @PostMapping
    @RequestMapping("/admin")
    String Admin(){
        return "admin";
    }
}
