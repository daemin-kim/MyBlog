package com.CNUSWAcademy.MyBlog.service;

import com.CNUSWAcademy.MyBlog.domain.RefreshToken;
import com.CNUSWAcademy.MyBlog.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    
    // 전달받은 리프레시 토큰으로 리프레시 토큰 객체를 검색해서 전달하는 findByRefreshToken() 메서드를 구현
    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}
