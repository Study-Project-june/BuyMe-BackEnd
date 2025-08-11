package com.test.buymebackend.domain.member.controller;


import com.test.buymebackend.domain.member.dto.request.MemberRequest;
import com.test.buymebackend.domain.member.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "회원 인증 API")
public class AuthController {
    private final AuthService authService;
    //TODO 회원가입 기능에 추가되지 않은 기능들 (이미지 파일 첨부 , request 중복 처리 , 비밀번호 encode 하기)
    @PostMapping(value = "/signup")
    @Operation(summary = "회원가입 API")
    //TODO basereponse 형태 만들기 이후 통일하기
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 가입에 성공했습니다.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "VALID001", description = "잘못된 입력값입니다",
                    content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<Void> signup(
            @Valid @RequestBody MemberRequest.SignUpRequest request
            //@RequestPart(name = "profileImage", required = false) MultipartFile profileImage
    ) {
        authService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //TODO spring security 와 jwt 로 로그인 기능 구현하기
    @PostMapping(value = "/login")
    @Operation(summary = "로그인 API")
    public ResponseEntity<String> login(
            @RequestBody MemberRequest.LoginRequest request
    ){
        String token = authService.login(request);
        return ResponseEntity.ok(token);
    }
}
