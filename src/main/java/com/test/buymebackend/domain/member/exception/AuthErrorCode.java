package com.test.buymebackend.domain.member.exception;

import com.test.buymebackend.global.common.ResultCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ResultCode {
    // 인증 공통 (001-009)
// 인증 공통 (001-009)
    UNAUTHORIZED("AUTH001", "인증되지 않은 사용자입니다.", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("AUTH002", "접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    AUTHENTICATION_FAILED("AUTH003", "인증에 실패하였습니다.", HttpStatus.UNAUTHORIZED),

    // 로그인 관련 (010-019)
    INVALID_PASSWORD("AUTH010", "잘못된 비밀번호입니다.", HttpStatus.UNAUTHORIZED),
    ACCOUNT_LOCKED("AUTH011", "계정이 잠겼습니다.", HttpStatus.LOCKED),
    ACCOUNT_DISABLED("AUTH012", "비활성화된 계정입니다.", HttpStatus.FORBIDDEN),

    // 토큰 관련 (030-039)
    INVALID_TOKEN("AUTH030", "유효하지 않은 토큰입니다.", HttpStatus.UNAUTHORIZED),
    EXPIRED_TOKEN("AUTH031", "만료된 토큰입니다.", HttpStatus.UNAUTHORIZED),
    UNSUPPORTED_TOKEN("AUTH032", "지원하지 않는 토큰입니다.", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN_FORMAT("AUTH033", "잘못된 토큰 형식입니다.", HttpStatus.BAD_REQUEST),
    REFRESH_TOKEN_NOT_FOUND("AUTH034", "리프레시 토큰을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    REFRESH_TOKEN_MISMATCH("AUTH035", "리프레시 토큰이 일치하지 않습니다.", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN_TYPE("AUTH036", "잘못된 토큰 타입입니다.", HttpStatus.BAD_REQUEST),

    // 요청 형식 오류
    INVALID_REQUEST_FORMAT("AUTH040", "잘못된 요청 형식입니다.", HttpStatus.BAD_REQUEST);
    private final String code;
    private final String message;
    private final HttpStatus status;
}
