package com.test.buymebackend.domain.store.controller;

import com.test.buymebackend.domain.enums.StoreCateory;
import com.test.buymebackend.domain.store.dto.response.StoreResponse;
import com.test.buymebackend.domain.store.service.StoreService;
import com.test.buymebackend.global.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Store API", description = "상점(가게) 관리 API")
@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;


    @Operation(summary = "상점 목록 조회", description = "카테고리별, 지역별 상점 리스트를 조회합니다.")
    @GetMapping
    public BaseResponse<StoreResponse.StoreResponseList> getStores(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) StoreCateory category
    ) {
        return BaseResponse.success("성공적으로 매장 정보를 불러왔습니다.",storeService.getStores(page,category));
    }

    @Operation(summary = "상점 상세 조회", description = "메뉴, 리뷰 포함 상점 상세 정보를 조회합니다.")
    @GetMapping("/{storeId}")
    public BaseResponse<StoreResponse.StoreSelectResponse> getStoreDetail(@PathVariable Long storeId) {
        return BaseResponse.success("매장아디기가 " + storeId +" 인 정보를 불러왔습니다." , storeService.getStoreDetail(storeId));
    }

    @Operation(summary = "상점 등록", description = "사장님이 가게 정보를 등록합니다.")
    @PostMapping
    public String createStore() {
        // TODO: 서비스 호출 후 BaseResponse 반환
        return "상점 등록";
    }

    @Operation(summary = "상점 정보 수정", description = "사장님이 가게 정보를 수정합니다.")
    @PatchMapping("/{storeId}")
    public String updateStore(@PathVariable Long storeId) {
        // TODO: 서비스 호출 후 BaseResponse 반환
        return "상점 수정: " + storeId;
    }

    @Operation(summary = "상점 상태 변경", description = "사장님이 가게 상태(영업/휴무)를 변경합니다.")
    @PatchMapping("/{storeId}/status")
    public String updateStoreStatus(@PathVariable Long storeId) {
        // TODO: 서비스 호출 후 BaseResponse 반환
        return "상점 상태 변경: " + storeId;
    }
}
