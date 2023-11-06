package com.example.SpringEvaluation.controller;

import com.example.SpringEvaluation.constants.ApiConstants;
import com.example.SpringEvaluation.constants.StringConstants;
import com.example.SpringEvaluation.dto.AuthRequest;
import com.example.SpringEvaluation.dto.ResponseMessage;
import com.example.SpringEvaluation.service.UserSessionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(ApiConstants.Session)
public class UserSessionController {
    @Autowired
    UserSessionService userSessionService;

    @PostMapping(ApiConstants.UserLogin)//create user sessions and provides token
    public ResponseEntity<ResponseMessage<String>> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        log.info(StringConstants.UserLoginRequested);
        return ResponseEntity.ok(new ResponseMessage<>((StringConstants.UserLoggedInSuccessfully),userSessionService.loginUser(authRequest)));
    }

    @PostMapping(ApiConstants.UserLogout)//user session is invalid
    public ResponseEntity<ResponseMessage<String>> userLogout(@RequestBody AuthRequest authRequest) {
        log.info(StringConstants.UserLogoutRequested);
        return ResponseEntity.ok(new ResponseMessage<>(userSessionService.logoutUser(authRequest)));
    }
}
