package com.test.buymebackend.domain.menu.entity;

import com.test.buymebackend.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuOption extends BaseEntity {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Menu menu;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;
}
