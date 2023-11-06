package com.example.SpringEvaluation.service;


import com.example.SpringEvaluation.constants.StringConstants;
import com.example.SpringEvaluation.dto.AuthRequest;
import com.example.SpringEvaluation.entity.UserSession;
import com.example.SpringEvaluation.exceptionHandler.DuplicateEntryException;
import com.example.SpringEvaluation.exceptionHandler.UserNotFoundException;
import com.example.SpringEvaluation.repository.UserDetailsRepository;
import com.example.SpringEvaluation.repository.UserSessionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
public class UserSessionServiceImpl implements UserSessionService {
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserSessionRepository userSessionRepository;
    @Autowired
    UserDetailsRepository userDetailsRepository;
    String  username;
    Long id;
    UserSession existingUserSession;

    /* * Authenticates a user based on the provided authentication request.
     */
    public boolean authenticateUser(AuthRequest authRequest)
    {
        log.info(StringConstants.UserSession+"authenticateUser");
        try
        {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
            // check whether the user credentials are present in database and authenticating
            if (authentication.isAuthenticated())
            {
                username = authRequest.getUserName();
                id = userDetailsRepository.findByUserName(username).get().getId();
                existingUserSession = userSessionRepository.findByUserId(id);
                return true;
            }
        }
        catch (AuthenticationException e)
        {
            throw new UserNotFoundException(StringConstants.UserNotFound);
        }
        return false;
    }

    /* * Logs a user in and generates an authentication token.
     */
    public String loginUser(AuthRequest authRequest)
    {
        log.info(StringConstants.UserSession+"loginUser");
        if(authenticateUser(authRequest))
        {
            if (existingUserSession == null)
            {
                String token = jwtService.generateToken(username);
                UserSession userSession = new UserSession();
                userSession.setUserId(id);
                userSession.setToken(token);
                userSessionRepository.save(userSession);
                // creating user session in database
                return token;
            }
            else
            {
                throw new DuplicateEntryException(StringConstants.UserAlreadyLoggedIn);
            }
        }
        else
        {
            throw new UserNotFoundException(StringConstants.UserNotFound);
        }

    }

    /* * Logs a user out by clearing their authentication token.
     */
    public String logoutUser(AuthRequest authRequest)
    {
        log.info(StringConstants.UserSession+"logoutUser");
        if(authenticateUser(authRequest))
        {
            if (existingUserSession != null) {
                userSessionRepository.deleteByUserId(id);
                //deleting the user session from database
                return StringConstants.LoggedOutSuccessfully;
            }
            else
            {
                throw new DuplicateEntryException(StringConstants.UserNotLoggedIn);

            }
        }
        else {
            throw new UserNotFoundException(StringConstants.UserNotFound);
        }
    }

}
