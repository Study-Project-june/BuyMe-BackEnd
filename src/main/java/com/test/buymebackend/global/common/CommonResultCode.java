package com.test.buymebackend.global.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonResultCode implements ResultCode {

    SUCCESS("200","성공적으로 처리됐습니다.", HttpStatus.ACCEPTED),;

    private final String code;
    private final String message;
    private final HttpStatus status;

}
