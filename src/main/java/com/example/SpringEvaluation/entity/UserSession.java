package com.example.SpringEvaluation.entity;

import lombok.Data;

import jakarta.persistence.*;


@Data
@Entity
public class UserSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long sessionId;
    String token;
    @Column(unique = true)
    Long userId;

}
