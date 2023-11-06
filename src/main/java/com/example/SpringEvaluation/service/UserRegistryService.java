package com.example.SpringEvaluation.service;

import com.example.SpringEvaluation.entity.UserDetail;

import jakarta.validation.Valid;

public interface UserRegistryService {
    UserDetail registerUser(@Valid UserDetail userDetail);
}
