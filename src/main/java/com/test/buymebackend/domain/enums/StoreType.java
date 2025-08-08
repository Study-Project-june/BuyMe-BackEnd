package com.test.buymebackend.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StoreType {
    KOREAN("korean", "한식"),
    CHINESE("chinese", "중식"),
    WESTERN("western", "양식"),
    JAPANESE("japanese", "일식"),
    SNACK("snack", "분식"),
    FASTFOOD("fastfood", "패스트푸드");
    private final String name;
    private final String description;
}
