package com.tutorial.jwtsecurity.controller.dto;

import com.tutorial.jwtsecurity.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String userId;

    public static UserResponseDto of(User user) {
        return new UserResponseDto(user.getUserId());
    }
}
