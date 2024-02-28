package com.example.vistreamv2.exception;

import com.example.vistreamv2.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response<String>> handelIllegalArgumentException(Exception ex){
        String getErrorMessage = ex.getMessage();
        return ResponseEntity.badRequest().body(Response.<String>builder()
                .error(getErrorMessage)
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Map<String, String>>> handelMethodArgumentNotValidException(MethodArgumentNotValidException ev){
        Map<String, String> setFieldsValidation = new HashMap<>();
        ev.getBindingResult().getFieldErrors().forEach(
                e -> setFieldsValidation.put(e.getField(), e.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(Response.<Map<String, String>>builder()
                .errorsValidation(setFieldsValidation)
                .build());
    }
}
