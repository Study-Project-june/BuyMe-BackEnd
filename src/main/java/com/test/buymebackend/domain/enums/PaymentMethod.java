package com.test.buymebackend.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public enum PaymentMethod {
    CASH("현금","만나서 현금 결제"),
    KAKAO_PAY("카카오페이","카카오페이 결제"),
    CREDIT_CARD("신용카드","만나서 신용카드 결제");

    private final String name;
    private final String description;
}
