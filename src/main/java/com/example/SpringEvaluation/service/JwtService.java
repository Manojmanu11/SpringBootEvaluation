package com.example.SpringEvaluation.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Log4j2
public class JwtService {
    @Value("${jwt.secret}")
    String secret; // Key to generate JWT token

    @Value("${jwt.expiration-time}")
    private Long expirationTime;

   @PostConstruct
    public void init() {
        SECRET = secret;
        EXPIRATION_TIME = expirationTime;
    }

    public static String SECRET;
    private static Long EXPIRATION_TIME;

    // Extract username from the token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract expiration time from the token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract claims from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Check whether the token is expired or not
    private Boolean isTokenExpired(String token) {
        Date expiration = extractExpiration(token);
        Date now = new Date();

        if (expiration.before(now)) {
            log.info("Token has expired. Expiration time: {}", expiration);
            return true;
        } else {
            log.info("Token is still valid. Expiration time: {}", expiration);
            return false;
        }
    }

    // Validate the token against the UserDetails
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        log.info(username);
        log.info(userDetails.getUsername());
        boolean tokenEx = isTokenExpired(token);
        System.out.println(tokenEx);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Generate a token
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    // Create a token with the provided claims and username
    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    // Get the key used to generate the token
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
