package com.test.buymebackend.domain.store.repository;

import com.test.buymebackend.domain.enums.StoreCateory;
import com.test.buymebackend.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Page<Store> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<Store> findByCategoryOrderByCreatedAtDesc(StoreCateory category, Pageable pageable);
}
