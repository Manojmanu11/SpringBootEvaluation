package com.example.SpringEvaluation.config;

import com.example.SpringEvaluation.filter.JwtAuthFilter;
import com.example.SpringEvaluation.service.UserInfoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration  // indicating that it contains configuration for the application.
@EnableWebSecurity // used to configure web-based security.
@EnableMethodSecurity // enables method-level security , allowing you to secure specific methods or functions with security rules.
public class SecurityConfig
{

    @Autowired
    JwtAuthFilter jwtAuthFilter;
    @Bean
    //authentication
    public UserDetailsService userDetailsService()
    {
        return new UserInfoUserDetailsService();
    }

    @Bean //Configures the main security filter chain
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/user/register","/userSession/userLogin","/quote/importSymbols").permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(invalidSessionEntryPoint()) // Set the custom entry point
                .and()
                .authorizeHttpRequests().requestMatchers("/dashboard/**","/quote/**","/dashboard/watchlist/addSymbols")
                .authenticated().and()
                .build();
    }

    @Bean //check for unauthorised requests
    public AuthenticationEntryPoint invalidSessionEntryPoint()
    {
        return (request, response, authException) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"error\": \"Invalid session\"}");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
    }
}