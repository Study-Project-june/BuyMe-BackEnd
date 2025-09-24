package com.test.buymebackend.domain.store.dto.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.buymebackend.domain.enums.StoreCateory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class StoreRequest {
    
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateStoreRequest {
        private String name;
        private StoreCateory category;
        private String address;
        private Integer minimumOrderPrice;
        private Integer deliveryFee;

        @Schema(type = "string", example = "09:00")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        private LocalTime openTime;

        @Schema(type = "string", example = "21:00")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        private LocalTime closeTime;
    }
}
