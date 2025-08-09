package com.test.buymebackend.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    CUSTOMER("손님"),
    OWNER("사장"),
    DELIVERY("배달기사"),
    ADMIN("관리자");


    private final String name;
}
