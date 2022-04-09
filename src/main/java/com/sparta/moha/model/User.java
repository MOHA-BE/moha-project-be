package com.sparta.moha.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {
    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String username; // dkdlel

    @Column(nullable = false, unique = true)
    private String nickname;

    @JsonIgnore // pw json에서 숨김처리
    @Column(nullable = false)
    private String password;

//    @Column(nullable = true)
//    private String user_profile;

//    @Column(nullable = false)
//    @Enumerated(value = EnumType.STRING)
//    private UserRoleEnum role;


}
