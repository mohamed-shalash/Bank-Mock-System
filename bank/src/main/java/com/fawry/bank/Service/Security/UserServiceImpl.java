package com.fawry.bank.Service.Security;

import com.fawry.bank.Models.UserModule;
import com.fawry.bank.Repos.Entity.Logs;
import com.fawry.bank.Repos.Entity.User;
import com.fawry.bank.Repos.LogRepo;
import com.fawry.bank.Repos.UserRepo;
import com.fawry.bank.Service.UserService;
import lombok.RequiredArgsConstructor;
/*import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;*/
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {//implements UserDetailsService {

    private final UserRepo userRepository;
    private final LogRepo logRepo;
    @Override
    public List<UserModule> getUsers() {
        return userRepository.findAll().stream().map(
                e-> UserModule.builder().
                        user_name(e.getUser_name())
                        //.password(e.getPassword())
                        .role(e.getRole())
                        .email(e.getEmail())
                        .build()
        ).toList();
    }

    @Override
    public UserModule getUser(String email, String password) {
        User user =userRepository.findByEmailAndPassword(email,password);
        return UserModule.builder()
                .role(user.getRole())
                .email(user.getEmail())
                .user_name(user.getUser_name())
                .build();
    }

    @Override
    @Transactional
    public void addUser(UserModule module) {
        User user =User.builder()
                .user_name(module.getUser_name())
                .role(module.getRole())
                .email(module.getEmail())
                .build();
        user.setPassword(module.getPassword());
        userRepository.save(user);

        Logs log =Logs.builder()
                .Date(LocalDate.now())
                .Time(LocalTime.now())
                .Kind("User_Add")
                .Log("Adding user "+user)
                .build();
        logRepo.save(log);
    }

    @Override
    @Transactional
    public void updateUser(UserModule module) {
        User user = userRepository.findByEmail(module.getEmail()).orElseThrow();

        Logs log =Logs.builder()
                .Date(LocalDate.now())
                .Time(LocalTime.now())
                .Kind("User_Update")
                .Log("Update user from "+user+" To "+module)
                .build();
        logRepo.save(log);

        user.setUser_name(module.getUser_name());
        user.setRole(module.getRole());
        user.setEmail(module.getEmail());
        userRepository.save(user);


    }

    @Override
    @Transactional
    public void delete(String email) {
        User user =userRepository.findByEmail(email).orElseThrow();
        userRepository.delete(user);

        Logs log =Logs.builder()
                .Date(LocalDate.now())
                .Time(LocalTime.now())
                .Kind("User_Delete")
                .Log("delete user "+user)
                .build();
        logRepo.save(log);
    }





/*

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email: "+ usernameOrEmail));


        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.getAuthorities());
    }*/
}