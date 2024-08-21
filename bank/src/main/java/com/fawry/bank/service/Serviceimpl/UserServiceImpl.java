package com.fawry.bank.service.Serviceimpl;

import com.fawry.bank.mapper.UserMapper;
import com.fawry.bank.models.UserModule;
import com.fawry.bank.entity.Logs;
import com.fawry.bank.entity.User;
import com.fawry.bank.repository.LogRepo;
import com.fawry.bank.repository.UserRepo;
import com.fawry.bank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepository;
    private final LogRepo logRepo;
    private final UserMapper userMapper;

    @Override
    public List<UserModule> getUsers() {
        return userRepository.findAll().stream().map(user-> userMapper.userToModuleMapper(user))
                        .toList();
    }

    @Override
    public UserModule getUser(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        return userMapper.userToModuleMapper(user);
    }

    @Override
    public List<UserModule> getUser(String role,Pageable pageable) {
        return userRepository.findByRole(role,pageable).stream().map(user -> userMapper.userToModuleMapper(user)).toList();
    }

    @Override
    public UserModule getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return userMapper.userToModuleMapper(user);
    }

    @Override
    @Transactional
    public void addUser(UserModule module) {
        User user = userMapper.moduleToUserMapper(module);
        userRepository.save(user);

        logRepo.save(
                setLog("User_Add","Adding user " + user
                        ,user.getEmail())
        );
    }

    @Override
    @Transactional
    public void updateUser(UserModule module) {
        User user = userRepository.findByEmail(module.getEmail()).orElseThrow();


        logRepo.save(
                setLog("User_Update","Update user from " + user + " To " + module
                        ,user.getEmail())
        );

        user.setUserName(module.getUserName());
        user.setRole(module.getRole());
        user.setEmail(module.getEmail());
        userRepository.save(user);


    }

    @Override
    @Transactional
    public void delete(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        userRepository.delete(user);


        logRepo.save(
                setLog("User_Delete","delete user " + user,user.getEmail())
        );
    }

    private Logs setLog(String kind,String logs,String email){
        return Logs.builder()
                .date(LocalDate.now())
                .time(LocalTime.now())
                .kind(kind)
                .log(logs)
                .email(email)
                .build();
    }
}