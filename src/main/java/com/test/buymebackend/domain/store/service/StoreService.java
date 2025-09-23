package com.test.buymebackend.domain.store.service;


import com.test.buymebackend.domain.enums.StoreCateory;
import com.test.buymebackend.domain.menu.dto.response.MenuResponse;
import com.test.buymebackend.domain.menu.repository.MenuRepository;
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
    private final MenuRepository menuRepository;

    public StoreResponse.StoreResponseList getStores(Integer page, StoreCateory category) {

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


        List<StoreResponse.StoreInfoResponse> storeInfoResponse = storePage.stream()
                .map(store -> {
                    boolean openNow = isOpenNow(store);
                    return storeMapper.toStoreResponse(store, openNow);
                })
                .toList();
        return new StoreResponse.StoreResponseList(storeInfoResponse);
    }

    private boolean isOpenNow(Store store) {
        LocalTime now = LocalTime.now();
        return !now.isBefore(store.getOpenTime()) && !now.isAfter(store.getCloseTime());
    }

    public StoreResponse.StoreSelectResponse getStoreDetail(Long storeId) {
        Store store = storeRepository.getStore(storeId);

        boolean openNow = isOpenNow(store);

        // 메뉴 조회
        List<MenuResponse.MenuDto> menus = menuRepository.findByStoreId(storeId).stream()
                .map(menu -> new MenuResponse.MenuDto(
                        menu.getName(),
                        menu.getPrice(),
                        menu.getDescription(),
                        menu.getImageUrl()
                ))
                .toList();

//        // 리뷰는 추후 수정
//        List<ReviewDto> reviews = reviewRepository.findByStoreId(storeId).stream()
//                .map(review -> new ReviewDto(
//                        review.getContent(),
//                        review.getRating(),
//                        review.getCreatedAt()
//                ))
//                .toList();

        return StoreResponse.StoreSelectResponse.builder()
                .name(store.getName())
                .storeCateory(store.getCategory())
                .address(store.getAddress())
                .minimumOrderPrice(store.getMinimumOrderPrice())
                .deliveryFee(store.getDeliveryFee())
                .openTime(store.getOpenTime())
                .closeTime(store.getCloseTime())
                .isOpenNow(openNow)
                .menus(menus)
                //.reviews(reviews)
                .build();
    }
}
