package com.test.buymebackend.config.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.test.buymebackend.config.security.auth.PrincipalDetail;
import com.test.buymebackend.domain.member.dto.request.MemberRequest;
import com.test.buymebackend.domain.member.exception.AuthErrorCode;
import com.test.buymebackend.global.common.BaseResponse;
import com.test.buymebackend.global.error.exception.CustomException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
        setFilterProcessesUrl("/api/auth/login"); // 해당 url 로 들어온 요청을 가로채기
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException{
        try {
            MemberRequest.LoginRequest loginDto = new ObjectMapper().readValue(
                    request.getInputStream(), MemberRequest.LoginRequest.class
            );

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(),
                    loginDto.getPassword()
            );

            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request, HttpServletResponse response,
            FilterChain chain, Authentication authResult
    ) throws IOException, ServletException {

        PrincipalDetail principalDetails = (PrincipalDetail) authResult.getPrincipal();
        String tokenResponse = "jwtToken : " +  jwtTokenProvider.createToken(
                principalDetails.getUsername(), principalDetails.member().getRole().name()
        );

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(
                BaseResponse.success("로그인에 성공했습니다.", tokenResponse)
        ));
    }

    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed
    ) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        BaseResponse<Void> errorResponse;


        if (failed instanceof InternalAuthenticationServiceException &&
                failed.getCause() instanceof CustomException customException) {
            errorResponse = BaseResponse.error(customException.getResultCode());
        } else if (failed instanceof BadCredentialsException) {
            errorResponse = BaseResponse.error(AuthErrorCode.INVALID_PASSWORD);
        } else {
            errorResponse = BaseResponse.error(AuthErrorCode.AUTHENTICATION_FAILED);
        }

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

}
