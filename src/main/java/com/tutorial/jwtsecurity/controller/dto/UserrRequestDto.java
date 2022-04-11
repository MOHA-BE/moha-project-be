package com.tutorial.jwtsecurity.controller.dto;

import com.tutorial.jwtsecurity.entity.Authority;
import com.tutorial.jwtsecurity.entity.User;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserrRequestDto {

    private String userId;
    private String password;

    public User toMember(PasswordEncoder passwordEncoder) {
        return User.builder()
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(userId, password);
    }
}
