package com.test.buymebackend.domain.member.mapper;

import com.test.buymebackend.domain.enums.UserRole;
import com.test.buymebackend.domain.member.dto.request.MemberRequest;
import com.test.buymebackend.domain.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member toEntity(MemberRequest.SignUpRequest request , String encodedPassword) {
        return Member.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .name(request.getName())
                .role(UserRole.CUSTOMER)
                .build();
    }
}
