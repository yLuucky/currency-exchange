package com.lucky.currencyexchange.user.infra.db.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "converter_user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id", nullable=false, unique=true)
    private UUID userId;
    @Column(name="nickname", length=60)
    private String nickname;
    @Column(name="email", nullable=false, unique=true)
    private String email;
    @Column(name="user_pass", length=72, nullable=false)
    private String password;

}
