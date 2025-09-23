package com.test.buymebackend.domain.store.service;


import com.test.buymebackend.domain.enums.StoreCateory;
import com.test.buymebackend.domain.store.dto.response.StoreResponse;
import com.test.buymebackend.domain.store.entity.Store;
import com.test.buymebackend.domain.store.mapper.StoreMapper;
import com.test.buymebackend.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    public StoreResponse.StoreReponseList getStores(Integer page, StoreCateory category) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<Store> storePage;
        if (category == null) {
            // 카테고리 없을 때 → 전체 매장을 생성일 순으로
            storePage  = storeRepository.findAllByOrderByCreatedAtDesc(pageable);
        } else {
            // 카테고리 있을 때 → 해당 카테고리 매장만
            storePage = storeRepository.findByCategoryOrderByCreatedAtDesc(
                    category, pageable
            );
        }


        List<StoreResponse.StoreReponse> storeResponses = storePage.stream()
                .map(store -> {
                    boolean openNow = isOpenNow(store);
                    return storeMapper.toStoreResponse(store, openNow);
                })
                .toList();
        return new StoreResponse.StoreReponseList(storeResponses);
    }

    private boolean isOpenNow(Store store) {
        LocalTime now = LocalTime.now();
        return !now.isBefore(store.getOpenTime()) && !now.isAfter(store.getCloseTime());
    }
}
