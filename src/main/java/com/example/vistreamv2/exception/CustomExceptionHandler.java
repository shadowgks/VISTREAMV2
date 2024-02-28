package com.example.vistreamv2.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail badCredentialsException(Exception ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
        problemDetail.setProperty("bad_credentials", "Authentication Failure");
        return problemDetail;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail accessDeniedException(Exception ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
        problemDetail.setProperty("access_denied", "Unauthorized Failure");
        return problemDetail;
    }

//    @ExceptionHandler(SignatureException.class)
//    public ProblemDetail signatureExceptionException(Exception ex){
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
//        problemDetail.setProperty("signature_exception", "JWT Signature not valid!");
//        return problemDetail;
//    }
//
//    @ExceptionHandler(ExpiredJwtException.class)
//    public ProblemDetail expiredJwtExceptionException(Exception ex){
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
//        problemDetail.setProperty("expiredJwt_exception", "JWT token already expired!");
//        return problemDetail;
//    }
}
