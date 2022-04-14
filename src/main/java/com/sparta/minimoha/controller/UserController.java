package com.sparta.minimoha.controller;

import com.sparta.minimoha.dto.SignUpRequestDto;
import com.sparta.minimoha.dto.UserRequestDto;
import com.sparta.minimoha.model.User;
import com.sparta.minimoha.security.jwt.JwtTokenProvider;
import com.sparta.minimoha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider){
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    //가입 요청 처리
    @PostMapping("/api/signup")
    public Map<String, String> registerUser(@RequestBody SignUpRequestDto requestDto) {
        User user = userService.registerUser(requestDto);
        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        result.put("id", String.valueOf(user.getId()));
        result.put("userId", user.getUserId());
        result.put("nickname", user.getNickname());

        return result;
    }


    // 로그인
    @PostMapping("/api/login")
    public Map<String,String> login(@RequestBody UserRequestDto requestDto) {
        User user = userService.login(requestDto);

        Map<String,String> result =new HashMap<>();
        result.put("token",jwtTokenProvider.createToken(user.getUserId(), user.getUserId(), user.getNickname())); // "username" : {username}
        result.put("userId", user.getUserId());
        result.put("id", String.valueOf(user.getId()));
        result.put("nickname", user.getNickname());
        result.put("result", "success");

        return result;
    }

    @PostMapping("/api/idCheck")
    public Map<String, String> duplicateId(@RequestBody UserRequestDto userRequestDto) {
        return userService.duplicateId(userRequestDto);
    }

    @PostMapping("/signup/duplicate_nickname")
    public Map<String, String> duplicateNickname(@RequestBody SignUpRequestDto signUpRequestDto) {
        return userService.duplicateNickname(signUpRequestDto);
    }

}
