package com.fawry.bank.Rest;



import java.util.Collections;
import java.util.Optional;

import com.fawry.bank.Repos.Entity.User;
import com.fawry.bank.Repos.UserRepo;
import com.fawry.bank.dto.LoginDto;
import com.fawry.bank.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/*import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

/*    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        System.out.println(loginDto);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

        // add check for username exists in a DB
        //System.out.println(userRepository.findByUsername(signUpDto.getUsername()).toString().equals( "Optional.empty"));
        if( !userRepository.findByUsername(signUpDto.getUsername()).toString().equals( "Optional.empty")){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }


        // create user object
        User user = new User();
        user.setUser_name(signUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setRole(signUpDto.getAuth());

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }*/
}
