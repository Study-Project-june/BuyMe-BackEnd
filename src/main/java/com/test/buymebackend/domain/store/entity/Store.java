package com.test.buymebackend.domain.store.entity;

import com.test.buymebackend.domain.enums.StoreType;
import com.test.buymebackend.domain.user.entity.User;
import com.test.buymebackend.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User owner;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StoreType category;

    @Column(nullable = false)
    private String address;

    @Builder.Default
    private Integer minimumOrderPrice = 0;

    @Column(nullable = false)
    private Integer deliveryFee;

    @Column(nullable = false)
    private LocalTime openTime;

    @Column(nullable = false)
    private LocalTime closeTime;
}

