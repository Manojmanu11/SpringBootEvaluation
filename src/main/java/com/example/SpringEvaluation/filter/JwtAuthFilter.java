package com.example.SpringEvaluation.filter;

import com.example.SpringEvaluation.service.JwtService;
import com.example.SpringEvaluation.service.UserInfoUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter /** creating filters that are executed once per HTTP request.*/
{
    @Autowired
    JwtService jwtService;

    @Autowired
    UserInfoUserDetailsService userInfoUserDetailsService;
    public static String userNameMatches;


    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException, IOException, ServletException {
        try{
            String authHeader = request.getHeader("Authorization");//retrieves the "Authorization" header from the incoming HTTP request, where JWT tokens are often included
            String token = null;//variable to store JWT token extracted from the header
            String username = null;//variable to store username extracted from token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                username = jwtService.extractUsername(token);
                userNameMatches = username;
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userInfoUserDetailsService                            .loadUserByUsername(username);
                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            // Handle exceptions (e.g., token validation failure) here
            SecurityContextHolder.clearContext(); // Clear authentication context
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
            return;
        }

        filterChain.doFilter(request, response);
    }

}