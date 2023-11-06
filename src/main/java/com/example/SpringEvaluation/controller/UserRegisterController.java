package com.example.SpringEvaluation.controller;



import com.example.SpringEvaluation.constants.ApiConstants;
import com.example.SpringEvaluation.constants.StringConstants;
import com.example.SpringEvaluation.dto.ResponseMessage;
import com.example.SpringEvaluation.entity.UserDetail;
import com.example.SpringEvaluation.service.UserRegistryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;


@Log4j2
@RequestMapping(ApiConstants.User)
@RestController
public class UserRegisterController {
    @Autowired
    UserRegistryService userRegistryService;
    @PostMapping(ApiConstants.UserRegistration)           //create a user
    public ResponseEntity<ResponseMessage<UserDetail>> addUser(@Valid @RequestBody UserDetail userDetail) {
        log.info(StringConstants.RequestToAddUser);
        return ResponseEntity.ok(new ResponseMessage<>((StringConstants.Userregistersuccess), userRegistryService.registerUser(userDetail)));
    }

}