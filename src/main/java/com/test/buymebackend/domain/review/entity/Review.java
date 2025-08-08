package com.test.buymebackend.domain.review.entity;

import com.test.buymebackend.domain.order.entity.Order;
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
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User customer;

    @Column(nullable = false)
    @Min(1)
    @Max(5)
    private Integer rating;

    private String comment;

    private String imageUrl;
}

