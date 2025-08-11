package com.test.buymebackend.config.security.auth;

import com.test.buymebackend.domain.member.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Member 엔티티를 직접 감싸서 Spring Security의 UserDetails로 사용합니다.
 * @param member 인증된 사용자 정보를 담고 있는 Member 엔티티
 */
public record PrincipalDetail(Member member) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Member 엔티티의 roles 리스트를 GrantedAuthority 컬렉션으로 변환합니다.
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + member.getRole().name()));
    }

    @Override
    public String getPassword() {
        // 비밀번호는 Member 엔티티에서 직접 가져옵니다.
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        // 사용자 ID는 Member 엔티티의 이메일입니다.
        return member.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료 여부 (true: 만료 안됨)
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠김 여부 (true: 잠기지 않음)
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 비밀번호 만료 여부 (true: 만료 안됨)
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정 활성화 여부 (true: 활성화됨)
        // 예: member.getDeletedAt() == null 과 같은 로직을 추가할 수 있습니다.
        return true;
    }
}
