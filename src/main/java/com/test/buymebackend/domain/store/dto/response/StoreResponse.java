package com.test.buymebackend.domain.store.dto.response;

import com.test.buymebackend.domain.enums.StoreCateory;
import com.test.buymebackend.domain.menu.dto.response.MenuResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

public class StoreResponse {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StoreInfoResponse {
        private String name;
        private StoreCateory storeCateory;
        private String address;
        private Integer minimumOrderPrice;
        private Integer deliveryFee;

        @Schema(type = "string", example = "21:00", description = "상점 오픈 시간 (HH:mm)")
        private LocalTime openTime;
        @Schema(type = "string", example = "21:00", description = "상점 마감 시간 (HH:mm)")
        private LocalTime closeTime;
        private boolean isOpenNow;
    }

    @Getter
    @AllArgsConstructor
    public static class StoreResponseList{
        private List<StoreInfoResponse> storeReponses;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StoreSelectResponse {
        private String name;
        private StoreCateory storeCateory;
        private String address;
        private Integer minimumOrderPrice;
        private Integer deliveryFee;
        @Schema(type = "string", example = "21:00", description = "상점 오픈 시간 (HH:mm)")
        private LocalTime openTime;
        @Schema(type = "string", example = "21:00", description = "상점 마감 시간 (HH:mm)")
        private LocalTime closeTime;
        private boolean isOpenNow;
        private List<MenuResponse.MenuDto> menus;
        //private List<ReviewDto> reviews; // 리뷰 엔티티 추후 추가
    }

}
