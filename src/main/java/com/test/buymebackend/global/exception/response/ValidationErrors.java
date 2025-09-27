package com.test.buymebackend.global.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationErrors {
    private List<ValidationError> errors;
    private String errorType;
}
