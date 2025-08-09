package com.test.buymebackend.domain.user.entity;

import com.test.buymebackend.domain.enums.UserRole;
import com.test.buymebackend.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
//TODO ERD 간단하게라도 짜서 코드 생성 후 entity 디렉토리 나눠서 만들어두자 또한 이후에 jwt 로그인 기능 구현하기

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long key;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;
}
