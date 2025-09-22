package com.test.buymebackend.global.error.exception;

import com.test.buymebackend.global.common.ResultCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public CustomException(ResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }
}
