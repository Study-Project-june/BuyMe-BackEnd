package com.test.buymebackend.global.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "API 응답 포맷")
public record BaseResponse<T>(
        @Schema(description = "응답 코드", example = "SUCCESS")
        String code,

        @Schema(description = "응답 메시지")
        String message,

        @Schema(description = "응답 데이터")
        T data,

        @Schema(description = "에러 발생시 클래스")
        String exception,

        @Schema(description = "응답 시간")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
        LocalDateTime timestamp
)
{

    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse<>(
                CommonResultCode.SUCCESS.getCode(),
                message,
                LocalDateTime.now(),
                data
        );
    }

    public static <T> BaseResponse<T> error(ResultCode resultCode) {
        return new BaseResponse<>(
                resultCode.getCode(),
                resultCode.getMessage(),
                LocalDateTime.now(),
                null
        );
    }

    public static <T> BaseResponse<T> error(ResultCode resultCode, String message) {
        return new BaseResponse<>(
                resultCode.getCode(),
                message,
                LocalDateTime.now(),
                null
        );
    }

    public static <T> BaseResponse<T> error(ResultCode resultCode, T errors) {
        return new BaseResponse<>(
                resultCode.getCode(),
                resultCode.getMessage(),
                LocalDateTime.now(),
                errors
        );
    }
}
