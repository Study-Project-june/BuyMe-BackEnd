package com.test.buymebackend.domain.store.mapper;


import com.test.buymebackend.domain.member.entity.Member;
import com.test.buymebackend.domain.store.dto.request.StoreRequest;
import com.test.buymebackend.domain.store.dto.response.StoreResponse;
import com.test.buymebackend.domain.store.entity.Store;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {
    public StoreResponse.StoreInfoResponse toStoreResponse(Store store , boolean isOpenNow) {
        return StoreResponse.StoreInfoResponse.builder()
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

    public Store toEntity(StoreRequest.CreateStoreRequest storeRequest , Member owner) {
        return Store.builder()
                .owner(owner)
                .name(storeRequest.getName())
                .category(storeRequest.getCategory())
                .address(storeRequest.getAddress())
                .minimumOrderPrice(storeRequest.getMinimumOrderPrice())
                .deliveryFee(storeRequest.getDeliveryFee())
                .openTime(storeRequest.getOpenTime())
                .closeTime(storeRequest.getCloseTime())
                .build();
    }
}
