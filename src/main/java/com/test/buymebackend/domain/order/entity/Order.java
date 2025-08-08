package com.test.buymebackend.domain.order.entity;

import com.test.buymebackend.domain.enums.OrderStatus;
import com.test.buymebackend.domain.store.entity.Store;
import com.test.buymebackend.domain.user.entity.User;
import com.test.buymebackend.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            // "Key" 컬럼

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User customer;          // "Key3" 컬럼

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Store store;        // "Key2" 컬럼

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status; // "Field" 컬럼

    @Column(nullable = false)
    private Long totalAmount;   // "Field2" 컬럼
}

