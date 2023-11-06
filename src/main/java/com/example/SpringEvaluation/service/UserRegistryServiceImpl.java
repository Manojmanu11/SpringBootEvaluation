package com.example.SpringEvaluation.service;

import com.example.SpringEvaluation.entity.UserDetail;
import com.example.SpringEvaluation.exceptionHandler.DuplicateEntryException;
import com.example.SpringEvaluation.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class UserRegistryServiceImpl implements UserRegistryService {
    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public UserDetail registerUser(@Valid UserDetail userDetail)
    {
        try{
            userDetail.setPassword(passwordEncoder.encode(userDetail.getPassword()));
            //encrypting the password and saving in the database
            userDetail.setCreated_Time(LocalDateTime.now());
            return userDetailsRepository.save(userDetail);
        }catch(Exception e)
        {
            Optional<UserDetail> emailExist = userDetailsRepository.findByEmail(userDetail.getEmail());
            //check email id is already present in user_detail table
            Optional<UserDetail> usernameExist = userDetailsRepository.findByUserName(userDetail.getUserName());
            //check username is already present in user_detail table
            if(emailExist.isPresent())
            {
                throw new DuplicateEntryException("Email Id Already exists");
            } else if (usernameExist.isPresent()) {
                throw new DuplicateEntryException("Username Already exist");
            }
            else
            {
                throw new RuntimeException(e);
            }
        }
    }
}

