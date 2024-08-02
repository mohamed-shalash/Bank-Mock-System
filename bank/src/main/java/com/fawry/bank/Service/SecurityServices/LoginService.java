package com.fawry.bank.Service.SecurityServices;

import com.fawry.bank.Repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepo repo;


}
