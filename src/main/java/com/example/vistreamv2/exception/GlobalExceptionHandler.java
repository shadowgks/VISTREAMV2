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
        Response<String> response = new Response<>();
        String getErrorMessage = ex.getMessage();
        response.setError(getErrorMessage);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Map<String, String>>> handelMethodArgumentNotValidException(MethodArgumentNotValidException ev){
        Response<Map<String, String>> response = new Response<>();
        Map<String, String> setFieldsValidation = new HashMap<>();
        ev.getBindingResult().getFieldErrors().forEach(
                e -> setFieldsValidation.put(e.getField(), e.getDefaultMessage())
        );
        response.setErrorsValidation(setFieldsValidation);
        return ResponseEntity.badRequest().body(response);
    }
}
