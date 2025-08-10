package com.test.buymebackend.domain.member.dto.request;

import com.test.buymebackend.domain.enums.UserRole;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class MemberRequest {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignUpRequest {
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;

        @NotBlank(message = "이름은 필수입니다.")
        @Size(min = 2, max = 10, message = "이름은 2자 이상 10자 이하여야 합니다.")
        private String name;

        @NotNull(message = "설정값은 기본입니다.")
        private UserRole role;

    }
}
