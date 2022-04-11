package com.tutorial.jwtsecurity.controller;

import com.tutorial.jwtsecurity.controller.dto.UserResponseDto;
import com.tutorial.jwtsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyInfo());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserInfo(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserInfo(userId));
    }
}