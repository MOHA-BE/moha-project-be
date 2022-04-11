package com.tutorial.jwtsecurity.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "user")
@Entity
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public User(String userId, String password, Authority authority) {
        this.userId = userId;
        this.password = password;
        this.authority = authority;
    }
}
