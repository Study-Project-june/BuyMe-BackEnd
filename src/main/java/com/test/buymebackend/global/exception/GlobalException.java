package com.test.buymebackend.global.exception;

import com.test.buymebackend.global.common.ResultCode;
import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {
    private final ResultCode resultCode;

    public GlobalException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public GlobalException(ResultCode resultCode,String message) {
        super(message != null ? message + " " + resultCode.getMessage()
                : resultCode.getMessage());
        this.resultCode = resultCode;
    }
}
