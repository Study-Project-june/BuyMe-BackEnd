package com.test.buymebackend.domain.menu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MenuResponse {


    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MenuDto {
        private String name;
        private Long price;
        private String description;
        private String imageUrl;
    }
}
