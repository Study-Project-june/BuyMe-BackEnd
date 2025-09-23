package com.test.buymebackend.domain.store.exception;


import com.test.buymebackend.global.common.ResultCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements ResultCode {


    // 상점 관련 에러
    STORE_NOT_FOUND("STORE_001", "해당 상점을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    STORE_ALREADY_EXISTS("STORE_002", "이미 존재하는 상점입니다.", HttpStatus.CONFLICT),
    STORE_ACCESS_DENIED("STORE_003", "해당 상점에 접근할 권한이 없습니다.", HttpStatus.FORBIDDEN),

    // 메뉴 관련 에러
    MENU_NOT_FOUND("STORE_004", "상점에 해당 메뉴가 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    MENU_ALREADY_EXISTS("STORE_005", "이미 존재하는 메뉴입니다.", HttpStatus.CONFLICT),

    // 리뷰 관련 에러
    REVIEW_NOT_FOUND("STORE_006", "리뷰를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    REVIEW_ALREADY_EXISTS("STORE_007", "이미 리뷰를 작성했습니다.", HttpStatus.CONFLICT),

    // 공통 에러
    INVALID_INPUT("STORE_008", "잘못된 입력 값입니다.", HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR("STORE_009", "서버 내부 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus status;
}
