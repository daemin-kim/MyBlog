package com.CNUSWAcademy.MyBlog.dto;

import lombok.Getter;
import lombok.Setter;

// 토큰 생성 요청 담당
@Getter
@Setter
public class CreateAccessTokenRequest {

    private String refreshToken;

}
