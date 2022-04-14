package com.sparta.minimoha.model;

import com.sparta.minimoha.dto.SignUpRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class User {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String nickname;

    @Column(nullable = true)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column(unique = true)
    private Long kakaoId;


    public User(String userId, String password, String nickname, UserRoleEnum role, Long kakaoId) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.kakaoId = kakaoId;
    }

    public User(String userId, String password, String nickname, UserRoleEnum role) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.kakaoId = null;
    }

    public User(SignUpRequestDto signUpRequestDto) {
        this.userId = signUpRequestDto.getUserId();
        this.password = signUpRequestDto.getPassword();
        this.nickname = signUpRequestDto.getNickname();
        this.kakaoId = null;
    }
}
