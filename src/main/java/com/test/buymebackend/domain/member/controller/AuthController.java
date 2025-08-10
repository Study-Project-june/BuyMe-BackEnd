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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "회원 인증 API")
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "/signup")
    @Operation(summary = "회원가입 API")
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
    @PostMapping(value = "/login")
    @Operation(summary = "로그인 API")
    public ResponseEntity<Void> login(

    ){
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
