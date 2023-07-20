package com.CNUSWAcademy.MyBlog.config;

import com.CNUSWAcademy.MyBlog.config.jwt.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
    필터는 실제로 각종 요청이 요청을 처리하기 위한 로직으로 전달되기 전후에 URL 패턴에 맞는 모든 요청을 처리하는 기능을 제공함.
    요청이 오면 헤더값을 비교해서 토큰이 있는지 확인하고 유효 토큰이라면 시큐리티 컨텍스트 홀더에 인증 정보를 저장함.

    시큐리티 컨텍스트는 인증 객체가 저장되는 보관소이며, 여기서 인증 정보가 필요할 때 언제든지 인증 객체를 꺼내 사용할 수 있음.
    이 클래스는 스레드 마다 공간을 할당하는 스레드 로컬에 저장됨으로 코드의 아무 곳에서나 참조할 수 있고,
    다른 스레드와 공유하지 않음으로 독립적으로 사용할 수 있음.
    그리고 이러한 시큐리티 컨텍스트 객체를 저장하는 객체가 시큐리티 컨텍스트 홀더임.
 */

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        
        // 요청 헤더의 Authorization 키의 값 조회
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        
        // 가져온 값에서 접두사(Bearer ) 제거
        String token = getAccessToken(authorizationHeader);
        
        // 가져온 토큰이 유효한지 확인하고, 유효한 때는 인증 정보를 설정
        if(tokenProvider.validToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }

    private String getAccessToken(String authorizationHeader) {
        // 만약 값이 null 이거나 Bearer로 시작하지 않으면 null을 반환함.
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

}
