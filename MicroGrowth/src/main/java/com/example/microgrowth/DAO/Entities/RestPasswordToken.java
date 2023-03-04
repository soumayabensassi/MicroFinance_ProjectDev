package com.example.microgrowth.DAO.Entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class RestPasswordToken {
    @SequenceGenerator(
            name = "password_token_sequence",
            sequenceName = "password_token_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "password_token_sequence"
    )
    private Long id;
    @Column(nullable=false)
    private String token;
    @Column(nullable=false)
    private LocalDateTime createdAt;
    @Column(nullable=false)
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(nullable=false,name="user_id")
    private User users;
    public RestPasswordToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt,User user) {
        super();
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.users= user;
    }


}