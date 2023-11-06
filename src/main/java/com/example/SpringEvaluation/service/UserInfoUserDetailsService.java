package com.example.SpringEvaluation.service;

import com.example.SpringEvaluation.constants.StringConstants;
import com.example.SpringEvaluation.entity.UserDetail;
import com.example.SpringEvaluation.entity.UserInfoUserDetails;
import com.example.SpringEvaluation.repository.UserDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Log4j2
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Start of the `loadUserByUsername` method in `UserInfoUserDetailsService`.");

        Optional<UserDetail> userInfoByUserName = userDetailsRepository.findByUserName(username);
        if (userInfoByUserName.isPresent()) {
            log.info("Found a user by username in the `repository`. Loading user details using `UserInfoUserDetails` for username: {}", username);
            return new UserInfoUserDetails(userInfoByUserName.get());//call userInfoUserDetails method with username
        }
        log.info("Attempting to find a user by email in the `repository`.");
        Optional<UserDetail> userInfoByEmail = userDetailsRepository.findByEmail(username);
        log.info("Found a user by email in the `repository`. Loading user details using `UserInfoUserDetails` for email: {}", username);
        return userInfoByEmail.map(userInfo -> new UserInfoUserDetails(userInfo, true))//call userInfoUserDetails with email as username
                .orElseThrow(() -> new UsernameNotFoundException(StringConstants.UserNotFound + username));
    }
}
