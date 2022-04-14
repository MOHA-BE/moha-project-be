package com.sparta.minimoha.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    private String userId;
    private String password;
    private String passwordCheck;
    private String nickname;
    private boolean admin = false;
    private String adminToken = "";
}
