package com.tutorial.jwtsecurity.controller;


import com.tutorial.jwtsecurity.controller.dto.UserrRequestDto;
import com.tutorial.jwtsecurity.controller.dto.UserResponseDto;
import com.tutorial.jwtsecurity.controller.dto.TokenRequestDto;
import com.tutorial.jwtsecurity.controller.dto.TokenDto;
import com.tutorial.jwtsecurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*") // 모든 출처에서 오는 요청을 허용하는 것이기 때문에 지양하는 것이 좋음
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserrRequestDto userrRequestDto) {
        return ResponseEntity.ok(authService.signup(userrRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody UserrRequestDto userrRequestDto) {
        return ResponseEntity.ok(authService.login(userrRequestDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}
