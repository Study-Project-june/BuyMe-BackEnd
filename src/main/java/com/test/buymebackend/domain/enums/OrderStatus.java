package com.test.buymebackend.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    ORDER_RECEIVED("ORDER_RECEIVED", "주문 접수"),
    PREPARING("PREPARING", "배달 준비 중"),
    OUT_FOR_DELIVERY("OUT_FOR_DELIVERY", "배달 중"),
    DELIVERED("DELIVERED", "배달 완료"),
    DELIVERY_FAILED("DELIVERY_FAILED", "배달 실패");
    //RETURNED("RETURNED", "반송 처리"); 다른곳에서 처리하는게 좋을듯
    private final String name;
    private final String description;
}
