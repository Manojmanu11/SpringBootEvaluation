package com.example.SpringEvaluation.entity;


import com.example.SpringEvaluation.constants.StringConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(unique = true)
        @NotBlank(message = StringConstants.UsernameBlank)
        private String userName;
        @NotBlank(message = StringConstants.PasswordBlank)
        @Pattern(regexp = StringConstants.PasswordPattern, message = StringConstants.PasswordConstraint)
        private String password;
        @Column(unique = true)
        @Email(message = StringConstants.InvalidEmail)
        private String email;
        private String roles = "ROLE_USER";

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<WatchList> watchlists = new ArrayList<>();

        @OneToMany(mappedBy = "userOrdersId", cascade = CascadeType.ALL)
        private List<Order> orders = new ArrayList<>();

        @OneToMany(mappedBy = "userTradeId", cascade = CascadeType.ALL)
        private List<Trade> trade = new ArrayList<>();

        private LocalDateTime created_Time;

}