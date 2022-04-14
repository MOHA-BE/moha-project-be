package com.sparta.minimoha.service;

import com.sparta.minimoha.dto.SignUpRequestDto;
import com.sparta.minimoha.dto.UserRequestDto;
import com.sparta.minimoha.model.User;
import com.sparta.minimoha.model.UserRoleEnum;
import com.sparta.minimoha.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User registerUser(SignUpRequestDto requestDto){

        // 패스워드 암호화
        String pw = passwordEncoder.encode(requestDto.getPassword());

        //가입 email(id) 중복체크
        String userId = requestDto.getUserId();

        //가입 nickname 중복체크
        String nickname = requestDto.getNickname();

        //비밀번호확인
        String password = requestDto.getPassword();
        String passwordCheck = requestDto.getPasswordCheck();

        //사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;

        User user = new User(userId, pw, nickname, role);
        return userRepository.save(user);
    }

    public User login(UserRequestDto requestDto) {
        User user = userRepository.findByUserId(requestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("id 를 찾을 수 없습니다"));

        // 패스워드 암호화
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("에러");
        }

        return user;
    }

    // 로그인 중복 id
    public Map<String, String> duplicateId(UserRequestDto userRequestDto) {
        User user = userRepository.findByUserId(userRequestDto.getUserId()).orElse(null);

        Map<String, String> result = new HashMap<>();
        if (user == null) {
            result.put("result", "success");
            return result;
        }

        result.put("result", "fail");
        result.put("message", "중복된 id가 존재합니다.");
        return result;
    }

    //로그인 중복 닉네임
    public Map<String, String> duplicateNickname(SignUpRequestDto signUpRequestDto) {
        User user = userRepository.findByNickname(signUpRequestDto.getNickname()).orElse(null);
        Map<String, String> result = new HashMap<>();
        if (user == null) {
            result.put("result", "success");
            return result;
        }

        result.put("result", "fail");
        result.put("message", "중복된 닉네임이 있습니다.");
        return result;
    }
}



