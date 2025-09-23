package com.test.buymebackend.domain.store.dto.request;


import com.test.buymebackend.domain.enums.StoreCateory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreRequest {

    @Getter
    @AllArgsConstructor
    public static class GetStores {
        private StoreCateory storeCateory;
        private int page;
    }
}
