package com.test.buymebackend.domain.member.service.auth;

import com.test.buymebackend.config.security.jwt.JwtTokenProvider;
import com.test.buymebackend.domain.member.dto.request.MemberRequest;
import com.test.buymebackend.domain.member.entity.Member;
import com.test.buymebackend.domain.member.mapper.MemberMapper;
import com.test.buymebackend.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Override
    public void signup(MemberRequest.SignUpRequest request) {
        Member member = memberMapper.toEntity(request , passwordEncoder.encode(request.getPassword())); //추후 수정
        memberRepository.save(member);
    }

}
