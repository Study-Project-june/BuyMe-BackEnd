package com.test.buymebackend.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentStatus {
    SUCCESS("결제 완료","결제 성공"),
    FAIL("결제 실패","결제 실패한 경우"),
    REFUND("환불 처리" , "주문취소 후 환불");

    private final String name;
    private final String description;
}
