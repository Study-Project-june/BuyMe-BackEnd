package com.test.buymebackend.global.error.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationErrors {
    private List<ValidationError> errors;
}
