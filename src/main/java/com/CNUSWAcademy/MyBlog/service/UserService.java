package com.CNUSWAcademy.MyBlog.service;

import com.CNUSWAcademy.MyBlog.domain.User;
import com.CNUSWAcademy.MyBlog.dto.AddUserRequest;
import com.CNUSWAcademy.MyBlog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/*
    리프레시 토큰을 전달받아 검증하고, 유효한 리프레시 토큰이라면 새로운 액세스 토큰을 생성하는 토큰 API를 구현함.
 */

@RequiredArgsConstructor
@Service
public class UserService {

//    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public Long save(AddUserRequest dto) {
//        return userRepository.save(User.builder()
//                .email(dto.getEmail())
//                // 패스워드 암호화
//                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
//                .build()).getId();
//    }
//
//    public User findById(Long userId) {
//        return userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
//    }

    private final UserRepository userRepository;

    public Long save(AddUserRequest dto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .build()).getId();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}
