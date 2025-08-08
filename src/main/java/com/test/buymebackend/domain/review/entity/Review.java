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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User customer;

    @Column(nullable = false)
    private Long rating;

    private String comment;

    private String imageUrl;
}

