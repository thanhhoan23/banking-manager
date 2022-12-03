package com.cg.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class AppUtil {

    public ResponseEntity<?> mapErrorToResponse(BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            System.out.println("fieldError.getField() => ");
            System.out.println(fieldError.getField());
            System.out.println("fieldError.getDefaultMessage() => ");
            System.out.println(fieldError.getDefaultMessage());
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
