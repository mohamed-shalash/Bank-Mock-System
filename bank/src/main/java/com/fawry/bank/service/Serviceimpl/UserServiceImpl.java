package com.fawry.bank.service.Serviceimpl;

import com.fawry.bank.handler.ConflictException;
import com.fawry.bank.handler.RecordNotFoundException;
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
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepository;
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
        User user = userRepository.findByEmail(email).orElseThrow(()->new RecordNotFoundException("User Email Dose Not Exist "));
        return userMapper.userToModuleMapper(user);
    }

    @Override
    @Transactional
    public void addUser(UserModule module) {
        if(!userRepository.findByEmail(module.getEmail()).equals( Optional.empty())) {
            throw new ConflictException("Email is already exists");
        }
        User user = userMapper.moduleToUserMapper(module);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(UserModule module) {
        User user = userRepository.findByEmail(module.getEmail()).orElseThrow(()->new RecordNotFoundException("User Email Dose Not Exist "));
        user.setUserName(module.getUserName());
        user.setRole(module.getRole());
        user.setEmail(module.getEmail());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()->new RecordNotFoundException("User Email Dose Not Exist "));
        userRepository.delete(user);



    }


}