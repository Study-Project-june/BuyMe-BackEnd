package com.test.buymebackend.config.security.jwt; // 사용자님의 패키지 경로에 맞게 수정하세요.

import com.test.buymebackend.config.SecurityConstant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 필터의 메인 로직입니다.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //헤더에서 필요없는 String 빼고 jwt 토큰 값만 가져오기
        String token = jwtTokenProvider.resolveToken(request);
        String path = request.getRequestURI();

        //TODO 예외처리 구현 try catch 를 통해
        System.out.println(">>> JwtAuthenticationFilter - Incoming Request Path: " + path);

        // 토큰이 존재하고 유효하다면, 인증 정보를 SecurityContext에 저장합니다.
        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 다음 필터로 요청을 전달합니다.
        filterChain.doFilter(request, response);
    }

    /**
     * 이 필터를 적용할 필요가 없는 경우를 결정하는 메서드입니다.
     * PUBLIC_URLS에 포함된 경로는 이 필터를 건너뜁니다.
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        // 공개 URL 목록 중 하나라도 현재 경로와 일치하면 true를 반환 (필터 실행 안 함)
        return Arrays.stream(SecurityConstant.PUBLIC_URLS)
                .anyMatch(pattern -> pathMatcher.match(pattern, path));
    }
}
