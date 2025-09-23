package com.test.buymebackend.global.exception;

import com.test.buymebackend.global.common.BaseResponse;
import com.test.buymebackend.global.error.CommonErrorCode;
import com.test.buymebackend.global.exception.response.ValidationError;
import com.test.buymebackend.global.exception.response.ValidationErrors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<BaseResponse<String>> handleMyException(GlobalException ex) {
        log.error("Exception: {}", ex.getMessage());

        BaseResponse<String> response = BaseResponse.error(ex.getResultCode(), ex.getMessage());
        return ResponseEntity.status(ex.getResultCode().getStatus()).body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<BaseResponse<Void>> handleAccessDeniedException(AccessDeniedException ex) {
        log.error("AccessDeniedException: {}", ex.getMessage());

        BaseResponse<Void> response = BaseResponse.error(CommonErrorCode.ACCESS_DENIED);
        return ResponseEntity.status(CommonErrorCode.ACCESS_DENIED.getStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<ValidationErrors>> handleValidationException(MethodArgumentNotValidException ex) {
        List<ValidationError> validationErrorList = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ValidationError(
                        error.getField(),
                        error.getDefaultMessage()
                ))
                .toList();

        validationErrorList.forEach(error ->
                log.error("Validation error - field: {}, message: {}", error.getField(), error.getMessage())
        );

        ValidationErrors validationErrors = new ValidationErrors(
                validationErrorList,
                ex.getClass().getSimpleName()
        );

        BaseResponse<ValidationErrors> response = BaseResponse.error(CommonErrorCode.INVALID_INPUT_VALUE, validationErrors);

        return ResponseEntity.status(CommonErrorCode.INVALID_INPUT_VALUE.getStatus())
                .body(response);
    }

    public record ErrorResponse(String code, String message, String exception) {}

}
