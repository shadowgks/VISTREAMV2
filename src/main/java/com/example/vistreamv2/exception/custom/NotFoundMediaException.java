package com.example.vistreamv2.exception.custom;

public class NotFoundMediaException extends RuntimeException{
    public NotFoundMediaException(String content){
        super(content);
    }
}
