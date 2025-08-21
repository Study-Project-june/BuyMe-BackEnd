package com.test.buymebackend.domain.member.validation.validator;

import com.test.buymebackend.domain.member.repository.MemberRepository;
import com.test.buymebackend.domain.member.validation.annotation.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final MemberRepository memberRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return memberRepository.findByEmail(email).isEmpty();
    }
}
