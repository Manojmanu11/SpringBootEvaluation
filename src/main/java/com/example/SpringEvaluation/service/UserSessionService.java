package com.example.SpringEvaluation.service;


import com.example.SpringEvaluation.dto.AuthRequest;

public interface UserSessionService {
    String loginUser(AuthRequest authRequest);
    String logoutUser(AuthRequest authRequest);
}
