package com.test.buymebackend.domain.member.service.auth;


import com.test.buymebackend.domain.member.dto.request.MemberRequest;

public interface AuthService {

    void signup(MemberRequest.SignUpRequest request);

    //String login(MemberRequest.LoginRequest request);
}
