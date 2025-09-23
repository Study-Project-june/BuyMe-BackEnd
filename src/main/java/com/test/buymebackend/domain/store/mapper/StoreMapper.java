package com.test.buymebackend.domain.store.mapper;


import com.test.buymebackend.domain.store.dto.response.StoreResponse;
import com.test.buymebackend.domain.store.entity.Store;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {
    public StoreResponse.StoreReponse toStoreResponse(Store store , boolean isOpenNow) {
        return StoreResponse.StoreReponse.builder()
                .name(store.getName())
                .storeCateory(store.getCategory())
                .address(store.getAddress())
                .minimumOrderPrice(store.getMinimumOrderPrice())
                .deliveryFee(store.getDeliveryFee())
                .openTime(store.getOpenTime())
                .closeTime(store.getCloseTime())
                .isOpenNow(isOpenNow)
                .build();
    }
}
