package com.test.buymebackend.domain.store.service;


import com.test.buymebackend.domain.enums.MemberRole;
import com.test.buymebackend.domain.enums.StoreCateory;
import com.test.buymebackend.domain.member.entity.Member;
import com.test.buymebackend.domain.menu.dto.response.MenuResponse;
import com.test.buymebackend.domain.menu.repository.MenuRepository;
import com.test.buymebackend.domain.store.dto.request.StoreRequest;
import com.test.buymebackend.domain.store.dto.response.StoreResponse;
import com.test.buymebackend.domain.store.entity.Store;
import com.test.buymebackend.domain.store.exception.StoreErrorCode;
import com.test.buymebackend.domain.store.mapper.StoreMapper;
import com.test.buymebackend.domain.store.repository.StoreRepository;
import com.test.buymebackend.global.error.CommonErrorCode;
import com.test.buymebackend.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;
    private final MenuRepository menuRepository;

    @Cacheable(cacheNames = "store:list", key = "T(java.util.Objects).toString(#page) + ':' + T(java.util.Objects).toString(#category)")
    @Transactional
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

    @Cacheable(cacheNames = "store:detail", key = "#storeId")
    @Transactional
    public StoreResponse.StoreSelectResponse getStoreDetail(Long storeId) {
//        log.info("[TEST] DB HIT - getStoreDetail({})", storeId);
//        System.out.println("[TEST] DB HIT - getStoreDetail(" + storeId + ")");
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

    @Transactional
    @CacheEvict(cacheNames = {"store:list"}, allEntries = true)
    public Store createStore(Member owner, StoreRequest.CreateStoreRequest request) {
        if(!owner.getRole().equals(MemberRole.OWNER)){
            throw new GlobalException(CommonErrorCode.ACCESS_DENIED , "가게 등록은 사장님만 가능합니다.");
        }
        boolean exists = storeRepository.existsByName(request.getName());
        if (exists) {
            throw new GlobalException(StoreErrorCode.STORE_ALREADY_EXISTS);
        }
        Store createdStore = storeMapper.toEntity(request , owner);
        storeRepository.save(createdStore);
        return createdStore;
    }

    @Transactional
    @CacheEvict(cacheNames = {"store:list", "store:detail", "store:menus"}, key = "#storeId", allEntries = false)
    public StoreResponse.StoreInfoResponse updateStore(Member owner ,Long storeId, StoreRequest.UpdateRequest request) {
        Store store = storeRepository.getStore(storeId);

        if (!Objects.equals(store.getOwner().getId(), owner.getId())) {
            throw new GlobalException(StoreErrorCode.STORE_ACCESS_DENIED);
        }

        if (request.getName() != null) store.setName(request.getName());
        if (request.getCategory() != null) store.setCategory(request.getCategory());
        if (request.getAddress() != null) store.setAddress(request.getAddress());
        if (request.getMinimumOrderPrice() != null) store.setMinimumOrderPrice(request.getMinimumOrderPrice());
        if (request.getDeliveryFee() != null) store.setDeliveryFee(request.getDeliveryFee());
        if (request.getOpenTime() != null) store.setOpenTime(request.getOpenTime());
        if (request.getCloseTime() != null) store.setCloseTime(request.getCloseTime());


        return storeMapper.toStoreResponse(store);
    }
}
