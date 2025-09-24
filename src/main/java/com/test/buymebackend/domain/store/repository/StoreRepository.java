package com.test.buymebackend.domain.store.repository;

import com.test.buymebackend.domain.enums.StoreCateory;
import com.test.buymebackend.domain.store.entity.Store;
import com.test.buymebackend.domain.store.exception.StoreErrorCode;
import com.test.buymebackend.global.exception.GlobalException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Page<Store> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<Store> findByCategoryOrderByCreatedAtDesc(StoreCateory category, Pageable pageable);

    default Store getStore(Long id) {
        return findById(id)
                .orElseThrow(() ->new GlobalException(StoreErrorCode.STORE_NOT_FOUND , id.toString() ));
    }

    boolean existsByName(String name);
}
