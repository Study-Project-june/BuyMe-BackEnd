package com.test.buymebackend.config.security.jwt;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.buymebackend.global.common.BaseResponse;
import com.test.buymebackend.global.error.CommonErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");

        BaseResponse<?> errorResponse =
                BaseResponse.error(CommonErrorCode.UNAUTHORIZED, "토큰이 유효하지 않거나 없습니다.");

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}