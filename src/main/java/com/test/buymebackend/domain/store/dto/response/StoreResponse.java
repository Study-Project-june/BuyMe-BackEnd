package com.test.buymebackend.domain.store.dto.response;

import com.test.buymebackend.domain.enums.StoreCateory;
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
    public static class StoreReponse {
        private String name;
        private StoreCateory storeCateory;
        private String address;
        private Integer minimumOrderPrice;
        private Integer deliveryFee;
        private LocalTime openTime;
        private LocalTime closeTime;
        private boolean isOpenNow;
    }

    @Getter
    @AllArgsConstructor
    public static class StoreReponseList{
        private List<StoreReponse> storeReponses;
    }
}
